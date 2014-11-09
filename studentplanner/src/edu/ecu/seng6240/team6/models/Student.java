package edu.ecu.seng6240.team6.models;

import com.google.gson.JsonObject;

public class Student extends User{

	public Student()
	{
		super();
		super.setRole(STUDENT_ROLE);
	}

	public Student(JsonObject jsonObject){
		super(jsonObject);
		this.setRole(STUDENT_ROLE);
	}
}

