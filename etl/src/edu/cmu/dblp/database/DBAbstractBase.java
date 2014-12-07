package edu.cmu.dblp.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
 * Code referenced from http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public abstract class DBAbstractBase<T> {
	
	/* 
	 * type: Refers to which java class is calling on this construct.
	 *       Maps to a corresponding database table.
	 */
	protected Class<T> type;
	
	/*
	 * connection: The class that handles the construction of the connection string.
	 */
	protected DBConnection dBConnection;
	
	/*
	 * explicitColumnNames: List of specific columns to be accessed
	 */
	protected List<String> explicitColumnNames;
	
	/*
	 * condition: Condition clause
	 */
	protected String condition;
	
	/*
	 * query: The final constructed SQL query
	 */
	protected final String query;
	
	// The constructor
	public DBAbstractBase(Class<T> type, DBConnection dBConnection, List<String> explicitColumnNames, String condition) {		
		this.type = type;
		this.dBConnection = dBConnection;
		this.explicitColumnNames = explicitColumnNames;
		this.condition = condition;
		this.query = createQuery();
	}
	
	// Abstract method to be implemented by the inheriting classes.
	protected abstract String createQuery();
	
	/*
	 * Returns the property names of this 'type', which corresponds to 
	 * the column names of the database table this 'type' refers to when asPlaceholder 
	 * is false. If, true, returns the '?' placeholder to construct the prepared statement
	 * Again, the explicit columns, if specified, ensures only those columns are returned.
	 * When calling all the columns, for insert the auto incremented id should not be present, 
	 * hence the RemoveIdColumn method is called in removeIdColumn value is true.
	 */
	public String getColumns(boolean asPlaceholder, boolean removeIdColumn)
	{
		StringBuilder returnString = new StringBuilder();
		boolean first = true;
		
		if(explicitColumnNames == null || explicitColumnNames.isEmpty())
		{
			Field[] fields;
			
			if(removeIdColumn)
			{
				fields = RemoveIdColumn(type.getDeclaredFields());
			}
			else
			{
				fields = type.getDeclaredFields();
			}
			
			Arrays.sort(fields, new Comparator<Field>() {
				  @Override
				  public int compare(Field f1, Field f2) {
				    return String.valueOf(f1.getName()).compareTo(f2.getName());
				  }
				});
			
			for(Field field:fields)
			{
				if(first)
				{
					first = false;
					if(asPlaceholder)
					{
						returnString.append("?");
					}
					else
					{
						returnString.append(field.getName());
					}
				}
				else
				{
					if(asPlaceholder)
					{
						returnString.append(", " + "?");
					}
					else
					{
						returnString.append(", " + field.getName());
					}
				}
			}
		}
		else
		{
			String[] explicitColumnNamesArray = explicitColumnNames.toArray(new String[explicitColumnNames.size()]);
			Arrays.sort(explicitColumnNamesArray);
			for(String columnName:explicitColumnNamesArray)
			{
				if(first)
				{
					first = false;
					if(asPlaceholder)
					{
						returnString.append("?");
					}
					else
					{
						returnString.append(columnName);
					}
				}
				else
				{
					if(asPlaceholder)
					{
						returnString.append(", " + "?");
					}
					else
					{
						returnString.append(", " + columnName);
					}
				}
			}
		}
		
		return returnString.toString();
		
	}
	
	/*
	 * Returns a Field array after removing the primary key column which is assumed to follow the naming 
	 * convention of 'TableName'+'Id'.
	 */
	public Field[] RemoveIdColumn(Field[] fields)
	{
		List<Field> returnFields = new ArrayList<Field>();
		for(Field field:fields)
		{
			if(!(field.getName().equalsIgnoreCase(type.getSimpleName()+"Id")))
			{
				returnFields.add(field);
			}
		}
		return returnFields.toArray(new Field[returnFields.size()]);
	}

}
