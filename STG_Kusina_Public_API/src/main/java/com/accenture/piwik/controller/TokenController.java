package com.accenture.piwik.controller;

import com.accenture.piwik.security.model.JwtPiwikAdalUser;
import com.accenture.piwik.security.model.JwtPiwikUser;
import com.accenture.piwik.security.model.JwtUser;
import com.accenture.piwik.service.UserService;
import com.accenture.piwik.utils.KusinaDateUtils;
import com.accenture.piwik.model.User;
import com.accenture.piwik.security.auth.JwtGenerator;
import com.accenture.piwik.security.auth.JwtValidator;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private JwtGenerator jwtGenerator;

    @Autowired
    private UserService userService;
    
    @Autowired
    private KusinaDateUtils kusinaDateUtils;
    
    @Autowired
    private JwtValidator validator;
    
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/token")
    public JSONObject generate(@RequestParam String eid) {
    	JwtPiwikUser jwtPiwikUser = null;
    	User user = userService.retrieveUserData(eid);
    	JSONObject result = new JSONObject();
    	
    	if (user != null) {
          if (kusinaDateUtils.convertStrDateToEpochMilli(user.getExpiryDate()) > new Date().getTime()) {
        	  
        	  	jwtPiwikUser = new JwtPiwikUser();
	          	jwtPiwikUser.setEid(user.getEid());
	          	jwtPiwikUser.setAirid(user.getAirid());
	          	jwtPiwikUser.setId(user.getId());
	          	jwtPiwikUser.setAccess(user.getAccess());
	          	jwtPiwikUser.setExpiryDate(user.getExpiryDate());
	          	
	          	result.put("token", jwtGenerator.generate(jwtPiwikUser));
	          	result.put("expiry", jwtPiwikUser.getExpiryDate());
             
          } else {
        	  result.put("status", "user already expired");
          }
      } else {
    	  result.put("status", "unauthorized user.");
      } 
    	
    	return result;
    }
}
