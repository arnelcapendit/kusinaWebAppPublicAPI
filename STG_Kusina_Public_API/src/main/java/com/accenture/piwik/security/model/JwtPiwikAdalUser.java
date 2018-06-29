package com.accenture.piwik.security.model;

public class JwtPiwikAdalUser {

	private String nonce;
	private String iat;
	private String sub;
	private String amr;
	private String auth_time;
	private String idp;
	private String upn;
	private String email;
	private String samaccount_name;
	private String peoplekey;
	private String personnelnbr;
	private String given_name;
	private String middle_name;
	private String sn;
	private String display_name;
	private String iss;
	private String aud;
	private String exp;
	private String nbf;
	
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getAmr() {
		return amr;
	}
	public void setAmr(String amr) {
		this.amr = amr;
	}
	public String getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(String auth_time) {
		this.auth_time = auth_time;
	}
	public String getIdp() {
		return idp;
	}
	public void setIdp(String idp) {
		this.idp = idp;
	}
	public String getUpn() {
		return upn;
	}
	public void setUpn(String upn) {
		this.upn = upn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSamaccount_name() {
		return samaccount_name;
	}
	public void setSamaccount_name(String samaccount_name) {
		this.samaccount_name = samaccount_name;
	}
	public String getPeoplekey() {
		return peoplekey;
	}
	public void setPeoplekey(String peoplekey) {
		this.peoplekey = peoplekey;
	}
	public String getPersonnelnbr() {
		return personnelnbr;
	}
	public void setPersonnelnbr(String personnelnbr) {
		this.personnelnbr = personnelnbr;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getNbf() {
		return nbf;
	}
	public void setNbf(String nbf) {
		this.nbf = nbf;
	}
	
	
}
