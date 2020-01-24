package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static String url;
	private static String user;
	private static String password;

	private static ConnectionFactory cf;

	// private static final String PROPERTIES_FILE =
	// "src/main/resources/database.properties";

	private ConnectionFactory() {
		Properties prop = new Properties();

		/*
		 * try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
		 * prop.load(fis); url = prop.getProperty("url"); user =
		 * prop.getProperty("user"); password = prop.getProperty("password"); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		url = System.getenv("POSTGRES_REVATURE2020");
		user = System.getenv("POSTGRES_REVATURE2020_USERNAME");
		password = System.getenv("POSTGRES_REVATURE2020_PASSWORD");
		
		//DO NOT DELETE THIS PART
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//DO NOT DELETE THIS PART
	}

	public static Connection getConnection() {
		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection();
	}

	private Connection createConnection() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error making Connection!");
		}

		return conn;
	}
}
