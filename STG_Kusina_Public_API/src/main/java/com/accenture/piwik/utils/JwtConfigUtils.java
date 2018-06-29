package com.accenture.piwik.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.mobile_expires_in}")
	private String jwtMobileExpires;
	
	@Value("${jwt.expires_in}")
	private String jwtExpires;
	
	@Value("${jwt.header}")
	private String jwtHeader;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public String getJwtMobileExpires() {
		return jwtMobileExpires;
	}

	public String getJwtExpires() {
		return jwtExpires;
	}

	public String getJwtHeader() {
		return jwtHeader;
	}
		
	
}
