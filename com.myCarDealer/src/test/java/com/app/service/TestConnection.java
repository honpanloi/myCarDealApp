package com.app.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestConnection {

	@Test
	void test() {
		Connection conn = null;
		
		String url2 = System.getenv("dburl");
		String username2 = System.getenv("dbUserName");
		String password2 = System.getenv("dbPassword");
		
		try {
			conn = DriverManager.getConnection(url2, username2, password2);
		} catch (SQLException e) {
			conn = null;
			e.printStackTrace();
		}
		
		assertNotNull(conn);
	}

}
