package com.cunzhi.entity;

import java.util.Date;

public class Text {
	private String text_id;
	private String text_title;
	private String text_content;
	private String email;
	private Date text_time;
	private String folder_id;
	private boolean marker;
	private Date text_revicetime;
	
	public Date getText_revicetime() {
		return text_revicetime;
	}
	public void setText_revicetime(Date text_revicetime) {
		this.text_revicetime = text_revicetime;
	}
	public boolean isMarker() {
		return marker;
	}
	public void setMarker(boolean marker) {
		this.marker = marker;
	}
	public String getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}
	public Date getText_time() {
		return text_time;
	}
	public void setText_time(Date text_time) {
		this.text_time = text_time;
	}
	public String getText_id() {
		return text_id;
	}
	public void setText_id(String text_id) {
		this.text_id = text_id;
	}
	public String getText_title() {
		return text_title;
	}
	public void setText_title(String text_title) {
		this.text_title = text_title;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
