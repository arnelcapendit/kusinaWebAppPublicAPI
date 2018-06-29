package com.accenture.piwik.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JwtSuccessHandler implements AuthenticationSuccessHandler{
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        System.out.println("Successfully Authentication");
//    }
	
private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
			throws IOException, ServletException {
		 System.out.println("Successfully Authentication");
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		authorities.forEach(authority -> {
//			if(authority.getAuthority().equals("Super Administrator")) {
//				try {
//					redirectStrategy.sendRedirect(arg0, arg1, "/hello");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else if(authority.getAuthority().equals("Project Administrator")) {
//				try {
//					redirectStrategy.sendRedirect(arg0, arg1, "/hello");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//	            throw new IllegalStateException();
//	        }
//		});
		
	}
 
}
