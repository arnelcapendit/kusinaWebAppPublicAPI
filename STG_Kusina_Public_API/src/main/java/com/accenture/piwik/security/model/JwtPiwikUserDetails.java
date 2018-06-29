package com.accenture.piwik.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtPiwikUserDetails implements UserDetails{

	private String eid;
	private String airid;
	private String id;
	private String access;
	private String expiryDate;
	private String token;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtPiwikUserDetails(String eid, String airid, String id, String access, String expiryDate, String token,
			List<GrantedAuthority> grantedAuthorities) {
		super();
		this.eid = eid;
		this.airid = airid;
		this.id = id;
		this.access = access;
		this.expiryDate = expiryDate;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return eid;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEid() {
		return eid;
	}

	public String getAirid() {
		return airid;
	}

	public String getId() {
		return id;
	}

	public String getAccess() {
		return access;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public String getToken() {
		return token;
	}
	
	
	
}
