package edu.cmu.dblp.database;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBSelectQueries<T> extends DBAbstractBase<T> {

	public DBSelectQueries(Class<T> type, DBConnection dBConnection, List<String> explicitColumnNames, String condition) {
		super(type, dBConnection, explicitColumnNames, condition);
	}

	@Override
	protected String createQuery() {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT ");
		query.append(super.getColumns(false, false));
		query.append(" FROM ");
		query.append(type.getSimpleName());
 		if(!(condition == null))
		{
			query.append(" WHERE " + condition);
		}
		
		return query.toString();
	}
	
	public List<T> getResults() throws SQLException, InstantiationException, IllegalAccessException, IntrospectionException, IllegalArgumentException, InvocationTargetException
	{
		List<T> results = new ArrayList<T>();
		
		Connection connection = null;
		try
		{
			connection = dBConnection.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next())
			{
				T instance = type.newInstance();
				
				for(Field field:type.getDeclaredFields())
				{
					if((super.explicitColumnNames != null && explicitColumnNames.contains(field.getName())) || super.explicitColumnNames == null || super.explicitColumnNames.isEmpty())
					{
						Object value = resultSet.getObject(field.getName());
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(instance, value);
					}
				}
				results.add(instance);
			}
		}
		finally
		{
			if(connection != null && !connection.isClosed())
			{
				connection.close();
			}
		}
		return results;		
	}

}
