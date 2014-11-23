package edu.ecu.seng6240.team6.models;

import com.google.gson.JsonObject;

public class Event {


	private int id=-1;
	private int userID=-1;
	private String title=null;
	private String tag=null;
	private String dateStr=null;
	private String timeStr=null;
	private String address=null;
	
	public Event()
	{
		
	}
	
	public Event(int userID,String title, String date, String time, String address,String tag)
	{
		this.userID=userID;
		this.title=title;
		this.tag=tag;
		this.dateStr=date;
		this.timeStr=time;
		this.address=address;
	}
	public Event(JsonObject jsonObject) {
		this.id=jsonObject.has("id")?jsonObject.get("id").getAsInt():-1;
		
		this.userID=jsonObject.has("userID")?jsonObject.get("userID").getAsInt():-1;
		this.title=jsonObject.has("title")?jsonObject.get("title").getAsString():null;
		
		this.dateStr=jsonObject.has("date")?jsonObject.get("date").getAsString():null;
		
		this.timeStr=jsonObject.has("time")?jsonObject.get("time").getAsString():null;
		
		this.tag =jsonObject.has("tag" )?jsonObject.get("tag").getAsString():null;
		
		this.address =jsonObject.has("address" )?jsonObject.get("address").getAsString():null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the dateStr
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * @param dateStr the dateStr to set
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
	 * @return the timeStr
	 */
	public String getTimeStr() {
		return timeStr;
	}

	/**
	 * @param timeStr the timeStr to set
	 */
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
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

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateStr == null) ? 0 : dateStr.hashCode());
		result = prime * result + id;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((timeStr == null) ? 0 : timeStr.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userID;
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateStr == null) {
			if (other.dateStr != null)
				return false;
		} else if (!dateStr.equals(other.dateStr))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (timeStr == null) {
			if (other.timeStr != null)
				return false;
		} else if (!timeStr.equals(other.timeStr))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", userID=" + userID + ", title=" + title
				+ ", tag=" + tag + ", dateStr=" + dateStr + ", timeStr="
				+ timeStr + ", address=" + address + "]";
	}
	
	

}
