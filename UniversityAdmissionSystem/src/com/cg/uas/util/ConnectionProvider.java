package com.cg.uas.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

	private String dbUserName;
	private String dbPassword;
	private String dbUrl;
	private String dbDriver;

	private static ConnectionProvider connectionProviderObject;

	private ConnectionProvider(String configFileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Properties props = new Properties();
		props.load(new FileInputStream(configFileName));

		dbUserName = props.getProperty("db.uid");
		dbPassword = props.getProperty("db.pwd");
		dbUrl = props.getProperty("db.url");
		dbDriver = props.getProperty("db.driver");

		Class.forName(dbDriver);
	}

	public static ConnectionProvider getInstance(String configFileName)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		if (connectionProviderObject == null)
			connectionProviderObject = new ConnectionProvider(configFileName);
		return connectionProviderObject;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}

}
