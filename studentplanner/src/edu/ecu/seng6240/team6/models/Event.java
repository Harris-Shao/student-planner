package edu.ecu.seng6240.team6.models;

import java.util.Random;

import com.google.gson.JsonObject;

public class Event {


	private int id=-1;
	private int userID = -1;
	private boolean isOwner = true;	
	private String start=null;
	private String end=null;
	private String text=null;
	private String value = null;
	private String resources = "A";
	
	public Event()
	{
		
	}
	
	
	public Event(JsonObject jsonObject) {
		
		this.id=jsonObject.has("id")?jsonObject.get("id").getAsInt():-1;		
		this.userID=jsonObject.has("userID")?jsonObject.get("userID").getAsInt():-1;
		this.isOwner=jsonObject.has("isOwner")?jsonObject.get("isOwner").getAsBoolean():true;
		
		this.start=jsonObject.has("start")?jsonObject.get("start").getAsString():null;
		
		this.end=jsonObject.has("end")?jsonObject.get("end").getAsString():null;
		
		this.text =jsonObject.has("text" )?jsonObject.get("text").getAsString():null;
		
		this.value =jsonObject.has("value" )?jsonObject.get("value").getAsString():null;
		this.resources =jsonObject.has("resources" )?jsonObject.get("resources").getAsString():"A";
	}

	
	public JsonObject toJsonObject(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", this.getId());
		jsonObject.addProperty("isOwner", this.isOwner);
		jsonObject.addProperty("userID", this.getUserID());
		jsonObject.addProperty("start", this.getStart());
		jsonObject.addProperty("end", this.getEnd());
		jsonObject.addProperty("text", this.getText());
		jsonObject.addProperty("value", this.getValue());
		jsonObject.addProperty("resources", this.getResources());
		return jsonObject;
	}
	


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public boolean isOwner() {
		return isOwner;
	}


	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getResources() {
		return resources;
	}


	public void setResources(String resources) {
		this.resources = resources;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = new Random().nextInt(20);

		result = prime * result + id;

		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;

		if (userID != other.userID)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", userID=" + userID + ", text" + text+"]";
	}
	
	

}
