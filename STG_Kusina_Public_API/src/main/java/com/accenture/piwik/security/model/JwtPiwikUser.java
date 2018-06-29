package com.accenture.piwik.security.model;

public class JwtPiwikUser {

	private String eid;
	private String airid;
	private String id;
	private String access;
	private String expiryDate;

	public String getEid() {
		return eid;
	}
	
	public void setEid(String eid) {
		this.eid = eid;
	}
	
	public String getAirid() {
		return airid;
	}
	
	public void setAirid(String airid) {
		this.airid = airid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccess() {
		return access;
	}
	
	public void setAccess(String access) {
		this.access = access;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}	
	
}
