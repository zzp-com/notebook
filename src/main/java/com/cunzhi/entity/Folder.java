package com.cunzhi.entity;

import java.util.Date;

public class Folder {
	private String folder_id;
	private String folder_title;
	private String email;
	private Date folder_time;
	private String folder_aid;
	private boolean marker;
	
	public boolean isMarker() {
		return marker;
	}
	public void setMarker(boolean marker) {
		this.marker = marker;
	}
	public String getFolder_aid() {
		return folder_aid;
	}
	public void setFolder_aid(String folder_aid) {
		this.folder_aid = folder_aid;
	}
	public Date getFolder_time() {
		return folder_time;
	}
	public void setFolder_time(Date folder_time) {
		this.folder_time = folder_time;
	}
	public String getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}
	public String getFolder_title() {
		return folder_title;
	}
	public void setFolder_title(String folder_title) {
		this.folder_title = folder_title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
