package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {
	
	public Helper(){
		
	}
	
	public static String ResultSetStringValue(ResultSet resultSet, String columnName) throws SQLException
	{
		String value = resultSet.getString(columnName);
		if(value.equals("null"))
		{
			value = null;
		}
		return value;
	}

}
