package com.crowleyworks.basicweb.model;

import java.util.Date;

/**
 * Manage a single activity within the system. An activity is some form of exercise
 * 
 * @author tdc
 *
 */
public class Activity {

	private int id;
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	private String userId;
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	
	private Date date;
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	
	private String location;
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	
	private String duration;
	public String getDuration() { return duration; }
	public void setDuration(String duration) { this.duration = duration; }
	
	private float distance;
	public float getDistance() { return distance; }
	public void setDistance(float distance) { this.distance = distance; }
	
	
}
