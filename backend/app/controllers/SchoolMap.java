package controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.School;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Helper;

public class SchoolMap extends Controller {
	
	public SchoolMap(){
		
	}
	
	public static Result GetSchoolsByTopic(String topic) throws SQLException, UnsupportedEncodingException
	{
		List<School> schools = null;
		topic = URLDecoder.decode(topic, "UTF-8");
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = util.SQLQueries.getSchoolsByTopic(connection, (topic.matches("All") ? "" : topic));
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			if(schools == null)
			{
				schools = new ArrayList<School>();
			}
			
			School school = new School();
			school.setSchoolId(resultSet.getInt("schoolId"));
			school.setSchoolName(Helper.ResultSetStringValue(resultSet, "schoolName"));
			school.setSchoolLocation(Helper.ResultSetStringValue(resultSet, "schoolLocation"));
			school.setRelevance(resultSet.getLong("count"));
			
			schools.add(school);
		}
	    
	    if (schools == null) {
	      return notFound("No data found");
	    }

	    return ok(Json.toJson(schools));
	}

}
