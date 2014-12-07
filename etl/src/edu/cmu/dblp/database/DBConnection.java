package edu.cmu.dblp.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
	
	// Opens a connection to the database
	public Connection createConnection() throws SQLException;
	
	// Returns the connection url
	public String getConnectionUrl();
}
