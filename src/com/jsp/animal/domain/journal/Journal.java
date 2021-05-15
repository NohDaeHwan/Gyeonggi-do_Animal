package com.jsp.animal.domain.journal;

import java.sql.Date;
import java.sql.Timestamp;

public class Journal {
	private int id;
	private int userId;
	private String title;
	private Date treatDate;
	private String visitHosptl;
	private int rank;
	private String content;
	private Timestamp createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public String getVisitHosptl() {
		return visitHosptl;
	}
	public void setVisitHosptl(String visitHosptl) {
		this.visitHosptl = visitHosptl;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
