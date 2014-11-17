package edu.ecu.seng6240.team6.models;

import com.google.gson.JsonObject;

public class Event {

	private int id=-1;
	
	private String title=null;
	private String tag=null;
	private String date=null;
	private String time=null;
	private String address=null;
	
	public Event()
	{
		
	}
	
	public Event(JsonObject jsonObject) {
		this.id=jsonObject.has("id")?jsonObject.get("id").getAsInt():-1;
		
		this.title=jsonObject.has("title")?jsonObject.get("title").getAsString():null;
		
		this.date=jsonObject.has("date")?jsonObject.get("date").getAsString():null;
		
		this.time=jsonObject.has("time")?jsonObject.get("time").getAsString():null;
		
		this.tag =jsonObject.has("tag" )?jsonObject.get("tag").getAsString():null;
		
		this.address =jsonObject.has("address" )?jsonObject.get("address").getAsString():null;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	

}
