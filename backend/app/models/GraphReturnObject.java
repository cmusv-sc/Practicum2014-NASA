package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import play.db.*;


// The model representation of the graph containing a list of nodes and a list of links.
public class GraphReturnObject {
	
	public List<Node> nodes;
	public List<Link> links;
	
	

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Node> getNodes() {
		if(nodes == null){
			return new ArrayList<Node>();
		}
		return nodes;
	}

	public List<Link> getLinks() {
		if(links == null){
			return new ArrayList<Link>();
		}
		return links;
	}

	public GraphReturnObject() {
		// TODO Auto-generated constructor stub
	}
	
	public void reset()
	{
		nodes = null;
		links = null;
	}
	
	// Sets the nodes and links to reflect Co-Authorship of the topic being queried. Topic 'All' returns all data  
	public void CoAuthorGraphDataByTopic(String parameter) throws NumberFormatException, Exception
	{
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = null;
		String[] topics = null;
		String separator = "@@@";
		//String allPublicationIds="";
		HashMap<String, Integer> nodeList = new HashMap<String, Integer>();
		
		String[] parameters = parameter.split("&");
		parameters[1] = parameters[1].equals("Publication") ? "publicationCount" : "citationCount";
		
		if(parameters[0].contains(","))
		{
			topics = parameters[0].split(",");
			preparedStatement = util.SQLQueries.getCoAuthorshipMultipleTopicsNodeInfo(connection, topics, parameters[1], Integer.parseInt(parameters[2])/topics.length);
		}
		else
		{
			preparedStatement = util.SQLQueries.getCoAuthorshipNodeInfo(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], Integer.parseInt(parameters[2]));
		}		
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			if(nodes==null)
			{
				nodes = new ArrayList<Node>();
			}
			
			//allPublicationIds += allPublicationIds.equals("") ? resultSet.getString("PublicationList") : "," + resultSet.getString("PublicationList");
			String[] publicationIds = resultSet.getString("PublicationList").split(",");
			StringBuilder publicationTitles = new StringBuilder();
			
			for(int i = 0; i< publicationIds.length; i++)
			{
				if(i != 0)
				{
					publicationTitles.append(separator);
				}
				publicationTitles.append(new Publication(Integer.parseInt(publicationIds[i]), connection).getPublicationTitle());
			}
			
			
			Node node = new Node("", topics == null ? parameters[0] : topics[resultSet.getInt("Topic")], resultSet.getString("AuthorName"), publicationTitles.toString(), resultSet.getString("authorId"), "Author", resultSet.getLong(parameters[1]));
			nodes.add(node);
			nodeList.put(node.getId(), 1);
		}
		
		if(topics != null)
		{
			preparedStatement = util.SQLQueries.getCoAuthorshipLinkInfoMultipleTopic(connection, topics, parameters[1], Integer.parseInt(parameters[2])/topics.length);
		}
		else
		{
			preparedStatement = util.SQLQueries.getCoAuthorshipLinkInfo(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], Integer.parseInt(parameters[2]));
		}
		
		resultSet = preparedStatement.executeQuery();
		
		
		HashMap<String, Long> coAuthorLinks = new HashMap<String, Long>();
		String edge = "";
		String mirrorEdge = "";
		while(resultSet.next())
		{
			if(links == null)
			{
				links = new ArrayList<Link>();
			}
			String[] authors = resultSet.getString("Authors").split(",");
			for(int i = 0; i < authors.length - 1; i++)
			{
				for(int j = i + 1; j < authors.length; j++)
				{
					edge = authors[i].trim() + "," + authors[j].trim();
					mirrorEdge = authors[j].trim() + "," + authors[i].trim();
					if(coAuthorLinks.containsKey(edge))
					{
						coAuthorLinks.put(edge, coAuthorLinks.get(edge).longValue() + 1);
					}
					else if (coAuthorLinks.containsKey(mirrorEdge))
					{
						coAuthorLinks.put(mirrorEdge, coAuthorLinks.get(mirrorEdge).longValue() + 1);
					}
					else
					{
						coAuthorLinks.put(edge.toString(), 1L);
					}
					edge = "";
					mirrorEdge = "";
				}
			}
		}
		
		preparedStatement.close();
		if(!connection.isClosed())
		{
			connection.close();
		}
		for(String key: coAuthorLinks.keySet())
		{
			String[] link = key.split(",");
			if(nodeList.containsKey(link[0]) && nodeList.containsKey(link[1]))
			{
				links.add(new Link(link[0], link[1], coAuthorLinks.get(key)));
			}
		}
	}

	public void AuthorPublicationGraphDataByTopic(String parameter) throws NumberFormatException, Exception
	{
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = null;
		String separator = "@@@";
		int limiter = 0;
		StringBuilder publicationTitles;
		List<Publication> publications;
		
		String[] parameters = parameter.split("&");
		parameters[1] = parameters[1].equals("Publication") ? "publicationCount" : "citationCount";
		
		int limit = Integer.parseInt(parameters[2]);
		
		preparedStatement = util.SQLQueries.getCoAuthorshipNodeInfo(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], limit);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next() && limiter < limit)
		{
			if(nodes==null)
			{
				nodes = new ArrayList<Node>();
			}
			
			String[] publicationIds = resultSet.getString("PublicationList").split(",");
			publicationTitles = new StringBuilder();
			publications = new ArrayList<Publication>();
			
			for(int i = 0; i< publicationIds.length; i++)
			{
				if(i != 0)
				{
					publicationTitles.append(separator);
				}
				Publication publication = new Publication(Integer.parseInt(publicationIds[i]), connection);
				publicationTitles.append(publication.getPublicationTitle());
				publications.add(publication);
			}
			
			Node node = new Node("", parameters[0], resultSet.getString("AuthorName"), resultSet.getString("PublicationList"), resultSet.getString("authorId"), "Author", resultSet.getLong(parameters[1] == "Publication" ? "publicationCount" : "citationCount"));
			nodes.add(node);
			limiter++;
			
			for(int i = 0; (i < publications.size() && limiter < limit); i++, limiter++)
			{
				String authorNames = getAuthorNamesForPublication(connection, publications.get(i).getPublicationId());
				Node publicationNode = new Node("", parameters[0], publications.get(i).getPublicationTitle(), authorNames, String.valueOf(publications.get(i).getPublicationId()), "Publication", publications.get(i).getCitationCount());
				nodes.add(publicationNode);
				if(links == null)
				{
					links = new ArrayList<Link>();
				}
				links.add(new Link(String.valueOf(node.getId()), String.valueOf(publicationNode.getId()), 1));
			}
		}
		
		if(!connection.isClosed())
		{
			preparedStatement.close();
			connection.close();
		}
	}

	private String getAuthorNamesForPublication(Connection connection, int publicationId) throws Exception {
		String authorNames = "";
		
		PreparedStatement preparedStatement = util.SQLQueries.getAuthorInfo(connection, publicationId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			authorNames += "@@@" + resultSet.getString("authorName");
		}
		preparedStatement.close();
		return authorNames.substring(3);
	}

	public void CoAuthorGraphDataByAuthor(String parameter) throws NumberFormatException, Exception
	{
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = null;
		String separator = "@@@";
		HashMap<String, Integer> nodeList = new HashMap<String, Integer>();
		
		String[] parameters = parameter.split("&");
		parameters[1] = parameters[1].equals("Publication") ? "publicationCount" : "citationCount";
		
		preparedStatement = util.SQLQueries.getCoAuthorByAuthor(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], Integer.parseInt(parameters[2]));
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			if(nodes==null)
			{
				nodes = new ArrayList<Node>();
			}
			
			String[] publicationIds = resultSet.getString("PublicationList").split(",");
			StringBuilder publicationTitles = new StringBuilder();
			
			for(int i = 0; i< publicationIds.length; i++)
			{
				if(i != 0)
				{
					publicationTitles.append(separator);
				}
				publicationTitles.append(new Publication(Integer.parseInt(publicationIds[i]), connection).getPublicationTitle());
			}
			
			Node node = new Node("", parameters[0], resultSet.getString("AuthorName"), publicationTitles.toString(), resultSet.getString("authorId"), "Author", resultSet.getLong(parameters[1]));
			nodes.add(node);
			nodeList.put(node.getId(), 1);
		}
		
		preparedStatement = util.SQLQueries.getCoAuthorLinkByAuthor(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], Integer.parseInt(parameters[2]));
		
		resultSet = preparedStatement.executeQuery();
		
		
		HashMap<String, Long> coAuthorLinks = new HashMap<String, Long>();
		String edge = "";
		String mirrorEdge = "";
		while(resultSet.next())
		{
			if(links == null)
			{
				links = new ArrayList<Link>();
			}
			String[] authors = resultSet.getString("Authors").split(",");
			for(int i = 0; i < authors.length - 1; i++)
			{
				for(int j = i + 1; j < authors.length; j++)
				{
					edge = authors[i].trim() + "," + authors[j].trim();
					mirrorEdge = authors[j].trim() + "," + authors[i].trim();
					if(coAuthorLinks.containsKey(edge))
					{
						coAuthorLinks.put(edge, coAuthorLinks.get(edge).longValue() + 1);
					}
					else if (coAuthorLinks.containsKey(mirrorEdge))
					{
						coAuthorLinks.put(mirrorEdge, coAuthorLinks.get(mirrorEdge).longValue() + 1);
					}
					else
					{
						coAuthorLinks.put(edge.toString(), 1L);
					}
					edge = "";
					mirrorEdge = "";
				}
			}
		}
		
		preparedStatement.close();
		if(!connection.isClosed())
		{
			connection.close();
		}
		
		for(String key: coAuthorLinks.keySet())
		{
			String[] link = key.split(",");
			if(nodeList.containsKey(link[0]) && nodeList.containsKey(link[1]))
			{
				links.add(new Link(link[0], link[1], coAuthorLinks.get(key)));
			}
		}
		
	}

	public void AuthorPublicationGraphDataByAuthor(String parameter) throws Exception
	{
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = null;
		String separator = "@@@";
		int limiter = 0;
		StringBuilder publicationTitles;
		List<Publication> publications;
		
		String[] parameters = parameter.split("&");
		parameters[1] = parameters[1].equals("Publication") ? "publicationCount" : "citationCount";
		
		int limit = Integer.parseInt(parameters[2]);
		
		preparedStatement = util.SQLQueries.getCoAuthorByAuthor(connection, (parameters[0].matches("All") ? "" : parameters[0]), parameters[1], limit);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next() && limiter < limit)
		{
			if(nodes==null)
			{
				nodes = new ArrayList<Node>();
			}
			
			String[] publicationIds = resultSet.getString("PublicationList").split(",");
			publicationTitles = new StringBuilder();
			publications = new ArrayList<Publication>();
			
			for(int i = 0; i< publicationIds.length; i++)
			{
				if(i != 0)
				{
					publicationTitles.append(separator);
				}
				Publication publication = new Publication(Integer.parseInt(publicationIds[i]), connection);
				publicationTitles.append(publication.getPublicationTitle());
				publications.add(publication);
			}
			
			Node node = new Node("", parameters[0], resultSet.getString("AuthorName"), resultSet.getString("PublicationList"), resultSet.getString("authorId"), "Author", resultSet.getLong(parameters[1] == "Publication" ? "publicationCount" : "citationCount"));
			nodes.add(node);
			limiter++;
			
			for(int i = 0; (i < publications.size() && limiter < limit); i++, limiter++)
			{
				Node publicationNode = new Node("", parameters[0], publications.get(i).getPublicationTitle(), "JishaSQL", String.valueOf(publications.get(i).getPublicationId()), "Publication", publications.get(i).getCitationCount());
				nodes.add(publicationNode);
				if(links == null)
				{
					links = new ArrayList<Link>();
				}
				links.add(new Link(String.valueOf(node.getId()), String.valueOf(publicationNode.getId()), 1));
			}
		}
		
		if(!connection.isClosed())
		{
			preparedStatement.close();
			connection.close();
		}
		
	}
}
