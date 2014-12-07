package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class to contain various sql queries to fetch relevant data to support the graphs at the front end 
// until a more elegant solution can be implemented. Generally takes a connection object and parameters 
// to construct and return a prepared statement
public class SQLQueries {

	/*
	 * SQL to set Node Info for author-author graph and author-publication map on search by topic
	 */
	public static PreparedStatement getCoAuthorshipNodeInfo(Connection connection, String topic, String sort, int limit) throws SQLException
	{
		/*String statement = "select rel.authorId,"
				+ "(select au.authorName from dblp.Author as au where au.authorId = rel.authorId) as AuthorName,"
				+ "GROUP_CONCAT((select CONCAT(p.publicationTitle, ' (', p.Year, ')') from dblp.Publication as p where p.publicationId = rel.publicationId) SEPARATOR '~') as PublicationList,"
				+ "COUNT(rel.publicationId) as PublicationCount "
				+ "from dblp.AuthorPublicationMap as rel "
				+ "where rel.publicationId in ("
				+ "select pub.publicationId from dblp.Publication as pub where pub.publicationTitle like ? "
				+ ") group by rel.authorId order by rel.authorId;";*/

		//Commented above and added below to send publication ids instead of publication titles

		String statement = "select "
				+ " a.authorId, a.authorName, "
				+ " GROUP_CONCAT(p.publicationId) PublicationList , "
				+ " COUNT(p.publicationId) publicationCount, "
				+ " SUM(p.citationCount) citationCount "
				+ " from  "
				+ " dblp.AuthorPublicationMap m, "
				+ " dblp.Publication p, "
				+ " dblp.Author a  "
				+ " where p.publicationTitle like ? "
				+ " and p.publicationId = m.publicationId  "
				+ " and m.authorId = a.authorId "
				+ " group by a.authorId "
				+ " order by ? desc "
				+ " limit ? "
				;

		PreparedStatement returnStatement = connection.prepareStatement(statement);

		returnStatement.setString(1, (topic.isEmpty() ? "%%" : ("%"+topic.trim()+"%")));

		returnStatement.setString(2, (sort.isEmpty() ? "1" : ("p."+sort)));
		returnStatement.setInt(3, limit);

		return returnStatement;
	}



	public static PreparedStatement getCoAuthorshipMultipleTopicsNodeInfo(Connection connection, String[] topic, String sort, int limit) throws SQLException
	{
		String statement = "select po.authorId, po.authorName, SUM(po.citationCount) citationCount,  "
				+" po.topic, GROUP_CONCAT(po.publicationId) as PublicationList from (  "
				+" (select p.publicationId, a.authorId, a.authorName, p.citationCount, 0 as topic   "
				+" from dblp.Publication p, dblp.Author a, dblp.AuthorPublicationMap m   "
				+" where p.publicationTitle like ?  "
				+" and m.publicationId = p.publicationId and a.authorId = m.authorId "
				+ "order by ? desc  limit ? )  "
				+" union all  "
				+" (select p.publicationId, a.authorId, a.authorName, p.citationCount, 1 as topic   "
				+" from dblp.Publication p, dblp.Author a, dblp.AuthorPublicationMap m   "
				+" where p.publicationTitle like ?  "
				+" and m.publicationId = p.publicationId and a.authorId = m.authorId "
				+ "order by ? desc limit ? )   "
				+" ) as po   "
				+" group by po.authorId  "
				+" order by ?  ";

		PreparedStatement returnStatement = connection.prepareStatement(statement);

		if(topic != null && topic.length >=2){
			returnStatement.setString(1, (topic[0].isEmpty() ? "%%" : ("%"+topic[0].trim()+"%")));
			returnStatement.setString(2, (sort.equals("Citation") ? "p.citationCount " : "p.citationCount desc"));
			returnStatement.setInt(3, limit);
			returnStatement.setString(4, (topic[1].isEmpty() ? "%%" : ("%"+topic[1].trim()+"%")));
			returnStatement.setString(5, (sort.equals("Citation") ? "p.citationCount desc" : "p.citationCount desc"));
			returnStatement.setInt(6, limit);
			returnStatement.setString(7, (sort.equals("Citation") ? "po.citationCount desc" : "po.citationCount desc"));
		}

		return returnStatement;
	}

	/*
	 * SQL to set Link Info for author-author graph and author-publication map on search by topic
	 */


	public static PreparedStatement getCoAuthorshipLinkInfo(Connection connection, String topic, String sort, int limit) throws SQLException
	{
		String statement = "select p.publicationId,GROUP_CONCAT(a.authorId) as Authors, "
				+ "count(p.publicationId) publicationCount "
				+ "from "
				+ "dblp.Publication p, "
				+ "dblp.AuthorPublicationMap m, "
				+ "dblp.Author a "
				+ "where p.publicationTitle like ? "
				+ "and p.publicationId = m.publicationId "
				+ "and m.authorId = a.authorId "
				+ "group by p.publicationId ";
		//		+ "order by ? desc "
		//		+ "limit ? ";
		/*String questionMark;
		String[] topics = topic.split(",");
		
		String statement = "select p.publicationId,GROUP_CONCAT(a.authorId) as Authors, "
				+ "count(p.publicationId) publicationCount "
				+ "from "
				+ "dblp.Publication p, "
				+ "dblp.AuthorPublicationMap m, "
				+ "dblp.Author a "
				+ "where p.publicationId in (";
		for(int i = 0; i < topics.length; i++)
		{
			statement += i==0 ? "?" : "," + "?";
		}
		statement += ") and p.publicationId = m.publicationId "
				+ "and m.authorId = a.authorId "
				+ "group by p.publicationId "
				;
		
		*/
		
		PreparedStatement returnStatement = connection.prepareStatement(statement);

		returnStatement.setString(1, (topic.isEmpty() ? "%%" : ("%"+topic.trim()+"%")));
		
		/*for(int i = 0; i < topics.length; i++)
		{
			returnStatement.setInt(i+1, Integer.parseInt(topics[i]));
		}*/

		//returnStatement.setString(2, (sort.isEmpty() ? "1" : sort));
		//returnStatement.setInt(3, limit);

		return returnStatement;
	}


	public static PreparedStatement getCoAuthorshipLinkInfoMultipleTopic(Connection connection, String[] topic, String sort, int limit) throws SQLException
	{
		String statement = "select links.publicationId, GROUP_CONCAT(links.authorId) as Authors from ( "
				+"(select rel.publicationId, rel.authorId from dblp.AuthorPublicationMap as rel " 
				+"inner join dblp.Publication as pub on rel.publicationId = pub.publicationId "
				+"where pub.publicationTitle like ? "
				+"order by ? "
				+"limit ?) "
				+"union all "
				+"(select rel.publicationId, rel.authorId from dblp.AuthorPublicationMap as rel " 
				+"inner join dblp.Publication as pub on rel.publicationId = pub.publicationId "
				+"where pub.publicationTitle like ? "
				+"order by ? "
				+"limit ?)"
				+") as links group by links.publicationId;";

		PreparedStatement returnStatement = connection.prepareStatement(statement);

		if(topic != null && topic.length >=2)
		{
			returnStatement.setString(1, (topic[0].isEmpty() ? "%%" : ("%"+topic[0].trim()+"%")));
			returnStatement.setString(2, sort.equals("") ? "pub.citationCount desc" : "pub.citationCount desc");
			returnStatement.setInt(3, limit);
			returnStatement.setString(4, (topic[1].isEmpty() ? "%%" : ("%"+topic[1].trim()+"%")));
			returnStatement.setString(5, sort.equals("") ? "pub.citationCount desc" : "pub.citationCount desc");
			returnStatement.setInt(6, limit);
		}

		return returnStatement;
	}

	/*
	 * SQL to get all the publications of the  author in a particular topic.
	 */
	/*public static PreparedStatement getPublicationInfo(Connection connection, int authorId, String topic) throws Exception{
		String statement = "";

		if(topic.isEmpty() || topic == null){//Calls this query if topic is null


			statement = "select p.* from dblp.Author a, dblp.Publication p ,dblp. AuthorPublicationMap map "
					+" where a.authorId = ? "
					+" and a.authorId = map.authorId"
					+" and map.publicationId = p.publicationId"
					+" and map.authorId = a.authorId "
					+" limit 100"
					;


			PreparedStatement returnStatement = connection.prepareStatement(statement);
			returnStatement.setInt(1, authorId);
			return returnStatement;
		}
		else{


			statement = "select p.* from dblp.Author a, dblp.Publication p ,dblp. AuthorPublicationMap map  "
					+" where a.authorId = ? "
					+" and a.authorId = map.authorId"
					+" and map.publicationId = p.publicationId"
					+" and map.authorId = a.authorId "
					+" and p.publicationTitle like ? "
					+" limit 100"
					;
			PreparedStatement returnStatement = connection.prepareStatement(statement);
			returnStatement.setInt(1, authorId);
			returnStatement.setString(2, (topic.isEmpty() ? "%%" : ("%"+topic+"%")));
			return returnStatement;
		}

	}*/



	/*
	 * SQL to get all user parameters
	 */
	public static PreparedStatement getUserInfo(Connection connection, String authorName) throws Exception{
		String statement ="select a.authorId,a.authorName, GROUP_CONCAT( p.PublicationId) publications from "
				+"dblp.Author a,"
				+"dblp.AuthorPublicationMap m,"
				+"dblp.Publication p "
				+"where a.authorName like ? "
				+"and a.authorId = m.authorId "
				+"and m.publicationId = p.publicationId "
				+"group by a.authorId,a.authorName "
				;

		PreparedStatement returnStatement = connection.prepareStatement(statement);

		//Setting the parameters
		returnStatement.setString(1, (authorName.isEmpty() ? "%%" : ("%"+authorName+"%")));
		return returnStatement;

	}
	/*
	 * getting publication details
	 */
	public static PreparedStatement getPublicationObject(Connection connection, int id) throws Exception{
		String statement ="select * from dblp.Publication where publicationId = ? "
				;


		PreparedStatement returnStatement = connection.prepareStatement(statement);

		//Setting the parameters
		returnStatement.setLong(1, id);
		return returnStatement;
	}

	public static PreparedStatement getSchoolsByTopic(Connection connection, String topic) throws SQLException
	{
		String statement = "select sch.schoolId, sch.schoolName, sch.schoolLocation, COUNT(*) as count from dblp.Publication as pub " + 
				"inner join dblp.PhdThesis as thesis on pub.publicationId = thesis.publicationId " +
				"inner join dblp.School as sch on thesis.schoolId = sch.schoolId " +
				"where pub.publicationTitle like ? " +
				"group by sch.schoolId, sch.schoolName, sch.schoolLocation "
				+ "limit 10";

		PreparedStatement returnStatement = connection.prepareStatement(statement);
		returnStatement.setString(1, (topic.isEmpty() ? "%%" : ("%"+topic+"%")));

		return returnStatement;
	}

	/*
	 * SQL to get all coAuthors
	 */
	public static PreparedStatement getCoAuthors(Connection connection,int authorId) throws Exception{

		String statement ="select a1.authorId coauthorId,a1.authorName coauthorName,group_concat(p.PublicationId) publications  "+
				"from dblp.AuthorPublicationMap m1 , dblp.Author a1, dblp.Publication p,( "+
				"select m.* from dblp.AuthorPublicationMap m, dblp.Publication p "+
				"where p.publicationId = m.publicationId "+
				"and m.authorId = ? "+
				") res "+
				"where res.publicationId = m1.publicationId "+
				"and m1.authorId = a1.authorId "+
				"and m1.publicationId = p.publicationId "+
				"group by a1.authorId,a1.authorName ";



		PreparedStatement returnStatement = connection.prepareStatement(statement);
		returnStatement.setInt(1, authorId);
		//returnStatement.setString(2, (topic.isEmpty() ? "%%" : ("%"+topic+"%")));
		return returnStatement;

	}

	/*
	 * SQL to get all authors in a particular field
	 * 
	 * 
	 */

	public static PreparedStatement getAuthors(Connection connection,String topic, Integer limit) throws Exception{

		String statement = "select a.authorId,a.authorName, GROUP_CONCAT( p.PublicationId) publications  from dblp.Author a, dblp.Publication p ,dblp. AuthorPublicationMap map "
				+" where a.authorId = map.authorId "
				+" and map.publicationId = p.publicationId "
				+" and map.authorId = a.authorId "
				+" and p.publicationTitle like ? "
				+"group by a.authorId,a.authorName "
				+" limit 100"
				;

		PreparedStatement returnStatement = connection.prepareStatement(statement);
		returnStatement.setString(1, (topic.isEmpty() ? "%%" : ("%"+topic+"%")));
		//returnStatement.setInt(2, limit);
		return returnStatement;

	}

	/*
	 * SQL to get the CoAuthorNodeInfo if queried by Author
	 */
	public static PreparedStatement getCoAuthorByAuthor(Connection connection,String author,String sort, int limit) throws Exception{

		/*String statement =
				"select a1.authorId,a1.authorName,GROUP_CONCAT( concat(p1.publicationTitle, '(',p1.year,')') SEPARATOR '~' ) PublicationList,count(*) PublicationCount ,SUM(p1.citationCount) citationCount "
						+"from dblp.Author a1, dblp.AuthorPublicationMap m1, dblp.Publication p1, "
						+"(select publicationId from dblp.AuthorPublicationMap m,dblp.Author a "
						+"where  m.authorId = a.authorId "
						+"and UPPER(a.authorName) = UPPER( ? )) r1 "
						+"where a1.authorId = m1.authorId  "
						+"and m1.publicationId = r1.publicationId "
						+"and m1.publicationId = p1.publicationId "
						+"group by a1.authorId " ;*/
		//Commented above and added below to send publication ids instead of titles
		String statement =
				"select a1.authorId,a1.authorName, "
						+" GROUP_CONCAT(p1.publicationId ) PublicationList, "
						+" count(*) PublicationCount , "
						+" SUM(p1.citationCount) citationCount " 
						+" from dblp.Author a1, dblp.AuthorPublicationMap m1, dblp.Publication p1,  "
						+" (select publicationId from dblp.AuthorPublicationMap m,dblp.Author a  "
						+" where  m.authorId = a.authorId  "
						+" and UPPER(a.authorName) = UPPER( ? )) r1  "
						+" where a1.authorId = m1.authorId   "
						+" and m1.publicationId = r1.publicationId  "
						+" and m1.publicationId = p1.publicationId  "
						+" group by a1.authorId "
						+" order by ? desc "
						+" limit ?" ;


		PreparedStatement returnStatement = connection.prepareStatement(statement);
		returnStatement.setString(1, author);
		returnStatement.setString(2, (sort.isEmpty() ? "1" : ("p1."+sort)));
		returnStatement.setInt(3, limit);

		return returnStatement;



	}

	/*
	 * SQL to get the CoAuthorLInkInfo if queried by Author
	 */
	public static PreparedStatement getCoAuthorLinkByAuthor(Connection connection,String author,String sort, int limit) throws Exception{

		String statement =
				"select p1.publicationId ,GROUP_CONCAT( a1.authorId ) Authors "
						+"from dblp.Author a1, dblp.AuthorPublicationMap m1, dblp.Publication p1, "
						+" (select publicationId from dblp.AuthorPublicationMap m,dblp.Author a "
						+"where  m.authorId = a.authorId "
						+"and UPPER(a.authorName) = UPPER( ? )) r1 "
						+"where a1.authorId = m1.authorId  "
						+"and m1.publicationId = r1.publicationId "
						+"and m1.publicationId = p1.publicationId "
						+"group by p1.publicationId  " 
						+"order by ? desc "
						+"limit ? "
						;

		PreparedStatement returnStatement = connection.prepareStatement(statement);
		returnStatement.setString(1, author);
		returnStatement.setString(2, (sort.isEmpty() ? "1" : ("p1."+sort)));
		returnStatement.setInt(3, limit);

		return returnStatement;



	}



	/*
	 * SQL to get all authors of a pubication
	 */
	public static PreparedStatement getAuthorInfo(Connection connection, int publicationId) throws Exception{
		String statement ="select a.authorId,a.authorName "
				+ "from "
				+"dblp.Author a,"
				+"dblp.AuthorPublicationMap m,"
				+"dblp.Publication p "
				+"where p.publicationId =  ? "
				+"and p.publicationId = m.publicationId "
				+"and m.authorId = a.authorId "
				;

		PreparedStatement returnStatement = connection.prepareStatement(statement);

		returnStatement.setInt(1, publicationId);
		return returnStatement;

	}


}
