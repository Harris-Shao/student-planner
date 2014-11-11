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

	private static final String SELECT_STUDENT_BY_USER_NAME ="SELECT * FROM User WHERE UserName = %s;";
	private static final String DELETE_STUDENT_BY_ID = "DELETE FROM User WHERE ID = %s";
	private static final String SELECT_ALL_USER = "SELECT * FROM User ORDER BY ID";
	private static final String INSERT_STUDENT = "INSERT INTO User (LastName, FirstName, Username)  VALUES (?,?,?);";
	
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
		List<User> users = new ArrayList<>(); 
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
