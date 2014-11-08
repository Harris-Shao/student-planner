package edu.ecu.seng6240.team6.Helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnectionManager {
	
	private static final String USER_NAME = null;
	private static final String PASS_WORD = null;
	private static final String SERVER= "localhost";
	private static final String PORT="3306";
	public static final String DB = "StudentPlanner";

	
	private static DataSource getMySqlDataSource() {
				
		Properties connectionProps = new Properties();
		connectionProps.put("user", USER_NAME);
		connectionProps.put("password", PASS_WORD);
		
		String url = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DB;
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(USER_NAME);
		mysqlDS.setPassword(PASS_WORD);
		
		return mysqlDS;
	}
	
	public static Connection getConnection() throws SQLException{
		DataSource ds = getMySqlDataSource();
		Connection con = null;
	    con = ds.getConnection();
		return con;
	}
}
