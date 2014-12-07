package edu.cmu.dblp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {
	
	static final String DB_URL = "jdbc:mysql://localhost/dblp";
	static final String USER = "root";
	static final String PASS = "";

	public MySQLConnection() {
		super();
	}

	@Override
	public Connection createConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		return DriverManager.getConnection(DB_URL,USER,PASS);

	}

	@Override
	public String getConnectionUrl() {
		return DB_URL;
	}
}
