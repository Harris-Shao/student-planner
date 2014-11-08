package edu.ecu.seng6240.team6.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ecu.seng6240.team6.models.Student;

public class UserDataManager {

	private static final String SELECT_STUDENT_BY_USER_NAME ="SELECT * FROM Student WHERE UserName = %s;";
	
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
			
		} catch (SQLException e) {
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
}
