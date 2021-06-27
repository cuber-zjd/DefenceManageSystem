package com.dy.utils;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtils {
	
	public static Connection getConnection() throws Exception {
		
		InputStream is=DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros=new Properties();
		pros.load(is);
		
		String USER=pros.getProperty("user");
		String PASSWORD=pros.getProperty("password");
		String URL=pros.getProperty("url");
		String DRIVER=pros.getProperty("driver");
		Connection conn=null;
		Class.forName(DRIVER);
		conn=java.sql.DriverManager.getConnection(URL,USER,PASSWORD);
		return conn;
	}
	
	public static void main(String[] args) throws Exception  {
		DBUtils.getConnection();
		
	}
}
