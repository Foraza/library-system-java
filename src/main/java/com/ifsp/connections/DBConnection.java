package com.ifsp.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private Connection conn = null;
	private String url = "jdbc:mariadb://localhost/final_project";
	private Properties connConfig = new Properties();
	//private String user = "root";
	//private String pswd = "password";
	
	public DBConnection() {
		connConfig.setProperty("user", "root");
		connConfig.setProperty("password", "password");
	}
	
	public Connection getConnection() {
		
		try {
			conn = DriverManager.getConnection(url, connConfig);
			System.out.println("SUUUCESSOOO");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
