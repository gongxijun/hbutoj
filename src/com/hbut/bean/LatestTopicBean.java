package com.hbut.bean;



import java.util.Date;


public class LatestTopicBean {


	private String username;
	private String title;
	private Date date;
	private Integer topicId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	@Override
	public String toString() {
		return "LatestTopicBean [username=" + username + ", title=" + title
				+ ", date=" + date + ", topicId=" + topicId
				+ ", getUsername()=" + getUsername() + ", getTitle()="
				+ getTitle() + ", getDate()=" + getDate() + ", getTopicId()="
				+ getTopicId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
