package edu.ecu.seng6240.team6.models;

import com.google.gson.JsonObject;

public class Admin extends User{
	
	public Admin(){
		super();
		super.setRole(ADIN_ROLE);
	}
	
	public Admin(JsonObject jsonObject){
		super(jsonObject);
		this.setRole(ADIN_ROLE);
	}
}
