package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
	
	private static Connection connection;
	
	private PostgresqlConnection() {
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		//Step 1 - load driver
		Class.forName("org.postgresql.Driver");
		
		//step 2 - open connection(url, username, password)
		String url = System.getenv("dburl");
		String username = System.getenv("dbUserName");
		String password = System.getenv("dbPassword");
		connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
	
}
