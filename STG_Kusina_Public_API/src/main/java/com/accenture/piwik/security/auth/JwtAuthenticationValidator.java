package com.accenture.piwik.security.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.accenture.piwik.security.model.JwtPiwikUserDetails;

@Component
public class JwtAuthenticationValidator {

	public JwtPiwikUserDetails authenticationCheck() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JwtPiwikUserDetails userDetails;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			 userDetails = (JwtPiwikUserDetails)SecurityContextHolder
					 .getContext()
					 .getAuthentication()
					 .getPrincipal();
		}else {
			throw new RuntimeException("unauthorized user");
		}		
		return userDetails;
	}
}
