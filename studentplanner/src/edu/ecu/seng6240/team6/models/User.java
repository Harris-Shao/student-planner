package edu.ecu.seng6240.team6.models;


public class User {
	public static final String STUDENT_ROLE = "User";
	public static final String ADIN_ROLE = "Admin";
	
	private int id = -1;
	private String lastName = null;
	private String firstName = null;
	private String userName = null;
	private String role = null;
	public User(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
