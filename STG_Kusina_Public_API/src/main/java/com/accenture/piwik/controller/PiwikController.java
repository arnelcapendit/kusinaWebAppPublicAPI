package com.accenture.piwik.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.piwik.model.Piwik;
import com.accenture.piwik.model.User;
import com.accenture.piwik.security.auth.JwtAuthenticationProvider;
import com.accenture.piwik.security.auth.JwtAuthenticationValidator;
import com.accenture.piwik.security.model.JwtPiwikUser;
import com.accenture.piwik.security.model.JwtPiwikUserDetails;
import com.accenture.piwik.service.PiwikService;
import com.accenture.piwik.service.UserService;

@RestController
@RequestMapping(value="piwik")
public class PiwikController {
	
	@Autowired
	private PiwikService piwikService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtAuthenticationValidator jwtAuthenticationValidator;
		
	@RequestMapping("/hello")
    String hello() {
        return "hello world";
    }
	
	@GetMapping("/data/{id}")
	public JSONObject piwikData(@PathVariable String id){		
		
		JwtPiwikUserDetails jwtPiwikUserDetails = jwtAuthenticationValidator.authenticationCheck();
		
		JSONObject result = new JSONObject();
		String[] idList = jwtPiwikUserDetails.getId().toString().split(",");
		List<String> newIdList = new ArrayList<>();
		
		for(String idItem : idList) {
			newIdList.add(idItem);
		}
		
		if(newIdList.contains(id)) {
			result.put("result", piwikService.retrieveAllData(id)); 
		}else {
			result.put("status", "Invalid ID - unauthorized user"); 
		}
		
		return result;
	}
	

}
