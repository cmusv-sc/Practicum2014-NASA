package edu.cmu.dblp.database;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DBInsertQueries<T> extends DBAbstractBase<T> {

	public DBInsertQueries(Class<T> type, DBConnection dBConnection,
			List<String> explicitColumnNames) {
		super(type, dBConnection, explicitColumnNames, null);
	}

	@Override
	protected String createQuery() {
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO ");
		query.append(type.getSimpleName());
		query.append(" (");
		query.append(super.getColumns(false, true));
		query.append(") VALUES (");
		query.append(super.getColumns(true, true));
		query.append(")");
		
		return query.toString();
	}
	
	public List<T> insertItems(List<T> items) throws SQLException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Connection connection = null;
		
		for(T item:items)
		{
			try
			{
				connection = new MySQLConnection().createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				
				if(explicitColumnNames == null || explicitColumnNames.isEmpty())
				{
					Field[] fields = RemoveIdColumn(type.getDeclaredFields());
					Arrays.sort(fields, new Comparator<Field>() {
						  @Override
						  public int compare(Field f1, Field f2) {
						    return String.valueOf(f1.getName()).compareTo(f2.getName());
						  }
						});
					
					int i = 1;
					
					for(Field field:fields)
					{
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getReadMethod();
						Object value = method.invoke(item);
						preparedStatement.setObject(i++, value);
					}
					preparedStatement.executeUpdate();
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if(resultSet.next())
					{
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(GetIdColumnName(), type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(item, resultSet.getInt(1));
					}
				}
				else
				{
					String[] explicitColumnNamesArray = explicitColumnNames.toArray(new String[explicitColumnNames.size()]);
					Arrays.sort(explicitColumnNamesArray);
					int i = 1;
					for(String columnName:explicitColumnNamesArray)
					{
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, type);
						Method method = propertyDescriptor.getReadMethod();
						Object value = method.invoke(item);
						preparedStatement.setObject(i++, value);
					}
					preparedStatement.executeUpdate();
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if(resultSet.next())
					{
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(GetIdColumnName(), type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(item, resultSet.getInt(1));
					}
				}
			}
			finally
			{
				if(connection != null && !connection.isClosed())
				{
					connection.close();
				}
			}
		}
		return items;
	}
	
	protected String GetIdColumnName()
	{
		String name = null;
		
		for(Field field:type.getDeclaredFields())
		{
			if(field.getName().equalsIgnoreCase(type.getSimpleName()+"Id"))
			{
				name = field.getName();
			}
		}
		return name;
	}

}
