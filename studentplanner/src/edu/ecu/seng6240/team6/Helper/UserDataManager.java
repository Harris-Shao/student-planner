package edu.ecu.seng6240.team6.Helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ecu.seng6240.team6.models.Student;
import edu.ecu.seng6240.team6.models.User;

public class UserDataManager {

	private static final String SELECT_STUDENT_BY_USER_NAME ="SELECT * FROM User WHERE UserName = '%s';";
	private static final String SELECT_STUDENT_BY_USER_EMAIL ="SELECT * FROM User WHERE UserEmail = '%s';";
	private static final String DELETE_STUDENT_BY_ID = "DELETE FROM User WHERE ID = %s";
	private static final String SELECT_ALL_USER = "SELECT * FROM User ORDER BY ID";
	private static final String INSERT_STUDENT = "INSERT INTO User (LastName, FirstName, Username, UserEmail, Password, Role)  VALUES (?,?,?, ?,?,?);";
	private static final String SELECT_LAST_INSERTION_ID = "SELECT LAST_INSERT_ID()";
	private static final String SELECT_STUDENT_BY_EMAIL_OR_USER_NAME_PASSWORD =
				"SELECT * FROM User WHERE (UserEmail = '%1$s' OR UserName = '%1$s') AND Password = '%2$s';";
	
	public static Student getStudentByUserName(String username){
		Student student = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(String.format(SELECT_STUDENT_BY_USER_NAME, username));
			rs = ps.executeQuery();
			while (rs.next())
			{
				student = new Student();
				student.setFirstName(rs.getString("FirstName"));
				student.setLastName(rs.getString("LastName"));
				student.setUserEmail(rs.getString("UserEmail"));
				student.setUserName(rs.getString("UserName"));
				student.setId(rs.getInt("ID"));
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (con != null) ps.close();
				} catch (SQLException e) {

				}			
		}
		return student;		
	}

	
	public static Student getStudentByUserEmail(String userEmail){
		Student student = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(String.format(SELECT_STUDENT_BY_USER_EMAIL, userEmail));
			rs = ps.executeQuery();
			while (rs.next())
			{
				student = new Student();
				student.setFirstName(rs.getString("FirstName"));
				student.setLastName(rs.getString("LastName"));
				student.setUserEmail(rs.getString("UserEmail"));
				student.setUserName(rs.getString("UserName"));
				student.setId(rs.getInt("ID"));
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (con != null) ps.close();
				} catch (SQLException e) {

				}			
		}
		return student;		
	}
	
	
	public static Student getStudentByUserNameOrEmailAndPassword(String emailOrUserName, String password){
		Student student = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(String.format(SELECT_STUDENT_BY_EMAIL_OR_USER_NAME_PASSWORD, emailOrUserName, password));
			rs = ps.executeQuery();
			while (rs.next())
			{
				student = new Student();
				student.setFirstName(rs.getString("FirstName"));
				student.setLastName(rs.getString("LastName"));
				student.setUserEmail(rs.getString("UserEmail"));
				student.setUserName(rs.getString("UserName"));
				student.setId(rs.getInt("ID"));
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (con != null) con.close();
				} catch (SQLException e) {

				}			
		}
		return student;	
	}
	
	public static boolean deleteStudent(int id) {
		String deleteString = String.format(DELETE_STUDENT_BY_ID, Integer.toString(id));
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(deleteString);
			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				success = true;
				con.commit();
			}
		} catch (SQLException | IOException e) {
			
		}
		finally{
				try {
					if (ps != null) ps.close();
					if (con != null) ps.close();
				} catch (SQLException e) {
					
				}			
		}
		return success;
	}

	public static boolean insert(Student student) {
		
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(INSERT_STUDENT);
			ps.setString(1, student.getLastName());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getUserName());
			ps.setString(4, student.getUserEmail());
			ps.setString(5, student.getPassword());
			ps.setString(6, student.getRole());

			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				success = true;
				con.commit();
			}
		} catch (SQLException | IOException e) {
			
		}
		finally{
				try {
					if (ps != null) ps.close();
					if (con != null) ps.close();
				} catch (SQLException e) {
					
				}			
		}
		return success;
	}

	public static boolean update(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	public static List<User> getAllUser() {
		List<User> users = new ArrayList<User>(); 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionManager.getConnection();
			ps = con.prepareStatement(UserDataManager.SELECT_ALL_USER);
			rs = ps.executeQuery();
			while (rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setUserName(rs.getString("UserName"));
				users.add(user);
			}

		} catch (SQLException | IOException e) {
			
		}
		finally{
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (con != null) ps.close();
				} catch (SQLException e) {
					
				}			
		}
		return users;
	}
}
