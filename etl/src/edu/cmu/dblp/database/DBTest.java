package edu.cmu.dblp.database;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.dblp.model.Author;

public class DBTest {

	public DBTest() {
		super();
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException
	{
		DBConnection dBConnection = new MySQLConnection();
		List<String> explicitColumnNames = new ArrayList<String>();
		//explicitColumnNames.add("authorName");
		//explicitColumnNames.add("authorId");
		explicitColumnNames = null;
		
		List<Author> authorsList = new ArrayList<Author>();
		Author authorInsert1 = new Author();
		//authorInsert1.setAuthorId(4);
		authorInsert1.setAuthorName("Mustafa");
		authorInsert1.setInstitution("CMU");
		authorsList.add(authorInsert1);
		Author authorInsert2 = new Author();
		//authorInsert2.setAuthorId(5);
		authorInsert2.setAuthorName("Jisha");
		authorInsert2.setInstitution("CMU");
		authorsList.add(authorInsert2);
		
		DBInsertQueries<Author> dBInsertQueries = new DBInsertQueries<Author>(Author.class, dBConnection, explicitColumnNames);
		authorsList = dBInsertQueries.insertItems(authorsList);
		
		List<Author> authors = new DBSelectQueries<Author>(Author.class, dBConnection, explicitColumnNames, null).getResults();
		
		for(Author author:authors)
		{
			System.out.println("ID: " + author.getAuthorId());
			System.out.println("Name: " + author.getAuthorName());
			System.out.println("Institution: " + author.getInstitution());
			System.out.println("==============");
		}
		
		for(Author author:authorsList)
		{
			System.out.println("ID: " + author.getAuthorId());
			System.out.println("Name: " + author.getAuthorName());
			System.out.println("Institution: " + author.getInstitution());
			System.out.println("==============");
		}
	}

}
