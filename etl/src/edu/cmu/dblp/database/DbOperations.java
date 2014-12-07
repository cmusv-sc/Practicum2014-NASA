package edu.cmu.dblp.database;

import java.sql.*;

public class DbOperations {

	private Connection connection = null;
	private Statement statement = null;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/test";
	//	//  Database credentials
	static final String USER = "Shuai";
	static final String PASS = "123456";

	public DbOperations() throws ClassNotFoundException, SQLException {
		super();
		Class.forName(JDBC_DRIVER);

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(DB_URL,"root","");

		System.out.println("Creating statement...");
		statement = connection.createStatement();
		// TODO Auto-generated constructor stub
	}

	public ResultSet callDatabaseQuery(String sqlQuery) throws SQLException {
		String sql;
		sql = sqlQuery;
		ResultSet rs = this.statement.executeQuery(sql);
		return rs;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DbOperations blah = new DbOperations();
		ResultSet rs = blah.callDatabaseQuery("SELECT * FROM coauthor.Coauthors");
		while(rs.next()){
			//Retrieve by column name
			String author = rs.getString("Author");
			String coauthor = rs.getString("Coauthor");

			//Display values
			System.out.print("Author 1: " + author);
			System.out.print("=====");
			System.out.println("Coauthor: " + coauthor);
		}
	}
}
