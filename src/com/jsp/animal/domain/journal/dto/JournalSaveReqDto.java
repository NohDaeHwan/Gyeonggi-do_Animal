package com.jsp.animal.domain.journal.dto;

import java.sql.Date;

public class JournalSaveReqDto {
	private int userId;
	private String title;
	private Date treatDate;
	private String content;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTreatDate() {
		return treatDate;
	}
	public void setTreatDate(Date treatDate) {
		this.treatDate = treatDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
