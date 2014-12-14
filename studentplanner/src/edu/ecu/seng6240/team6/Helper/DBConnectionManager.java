package edu.ecu.seng6240.team6.Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnectionManager {

	private static final String USER_NAME = "username";
	private static final String PASS_WORD = "password";
	private static final String SERVER = "server";
	private static final String PORT = "port";
	public static final String DB = "StudentPlanner";

	private static DataSource getMySqlDataSource() throws IOException {

		Properties connectionProps = new Properties();

		String osName = System.getProperty("os.name");

		String inputFile = null;

		if (osName.toLowerCase().startsWith("win")) {
			// in windows
			inputFile = "c:\\mysql\\user.properties";
		} else { // in mac, linux, unix system

			inputFile = "/opt/edu/ecu/user.properties";
		}

		FileInputStream in = new FileInputStream(inputFile);

		connectionProps.load(in);
		in.close();
		
		String server = connectionProps.getProperty(SERVER);
		String port= connectionProps.getProperty(PORT);
		String username = connectionProps.getProperty(USER_NAME);
		String password= connectionProps.getProperty(PASS_WORD);		
		String testdb="StudentPlanner";
		String url = "jdbc:mysql://" + server + ":" + port + "/" + testdb+"?relaxAutoCommit=true";
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		
		mysqlDS.setUser(username);
		mysqlDS.setPassword(password);
		
		/*
		mysqlDS.setUser(connectionProps.getProperty(USER_NAME));
		mysqlDS.setPassword(connectionProps.getProperty(PASS_WORD));
		*/
		return mysqlDS;
	}

	public static Connection getConnection() throws SQLException, IOException {
		DataSource ds = getMySqlDataSource();
		Connection con = null;
		con = ds.getConnection();
		return con;
	}
}
