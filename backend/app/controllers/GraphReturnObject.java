package controllers;

import java.sql.SQLException;

import play.libs.Json;
import play.mvc.*;
import trustprocessor.DBLPTrustProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class GraphReturnObject extends Controller{

	public GraphReturnObject() {
		// TODO Auto-generated constructor stub
	}
	
	// Controller to receive request for data required to render Co-Author graph by topic. Receives the topic as a 
	// String parameter encoded as a URL in UTF-8. Calls on the model by the same name to contact the database 
	// for data and returns the model as a Json object
	public static Result getCoAuthorGraphDataByTopic(String parameter) throws NumberFormatException, Exception
	{
		parameter = URLDecoder.decode(parameter, "UTF-8");
		models.GraphReturnObject graphReturnObject = new models.GraphReturnObject();
		
		String[] params = parameter.split("&");
		
		if(params[3].equals("true")){
			try {
				DBLPTrustProcessor proc = new DBLPTrustProcessor();
				proc.setTrustRelatedObjects(params[0], Integer.parseInt(params[2]));
				graphReturnObject = proc.retrieveTrustBasedGraph(params[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			graphReturnObject.CoAuthorGraphDataByTopic(parameter);
		}
	    
	    if (graphReturnObject.nodes == null) {
	      return notFound("No data found");
	    }

	    return ok(Json.toJson(graphReturnObject));
	}
	
	// Controller to receive request for data required to render Author Publication graph by topic. Receives the topic as a 
	// String parameter encoded as a URL in UTF-8. Calls on the model by the same name to contact the database 
	// for data and returns the model as a Json object
	public static Result getAuthorPublicationGraphDataByTopic(String parameter) throws NumberFormatException, Exception
	{
		parameter = URLDecoder.decode(parameter, "UTF-8");
		models.GraphReturnObject graphReturnObject = new models.GraphReturnObject();

		graphReturnObject.AuthorPublicationGraphDataByTopic(parameter);

		if (graphReturnObject.nodes == null) {
			return notFound("No data found");
		}

		return ok(Json.toJson(graphReturnObject));
	}
	
	// Controller to receive request for data required to render Co-Author graph by author. Receives the author name as a 
	// String parameter encoded as a URL in UTF-8. Calls on the model by the same name to contact the database 
	// for data and returns the model as a Json object
	public static Result getCoAuthorGraphDataByAuthor(String parameter) throws NumberFormatException, Exception
	{
		parameter = URLDecoder.decode(parameter, "UTF-8");
		models.GraphReturnObject graphReturnObject = new models.GraphReturnObject();

		graphReturnObject.CoAuthorGraphDataByAuthor(parameter);

		if (graphReturnObject.nodes == null) {
			return notFound("No data found");
		}

		return ok(Json.toJson(graphReturnObject));
	}

	// Controller to receive request for data required to render Author Publication graph by author. Receives the author name as a 
	// String parameter encoded as a URL in UTF-8. Calls on the model by the same name to contact the database 
	// for data and returns the model as a Json object
	public static Result getAuthorPublicationGraphDataByAuthor(String parameter) throws Exception
	{
		parameter = URLDecoder.decode(parameter, "UTF-8");
		models.GraphReturnObject graphReturnObject = new models.GraphReturnObject();

		graphReturnObject.AuthorPublicationGraphDataByAuthor(parameter);

		if (graphReturnObject.nodes == null) {
			return notFound("No data found");
		}

		return ok(Json.toJson(graphReturnObject));
	}

}
