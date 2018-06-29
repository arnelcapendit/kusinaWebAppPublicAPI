package com.accenture.piwik.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {   	
        return "Hello World ";
    }
     
    @GetMapping("/hi")
    public String hi(HttpServletRequest request, HttpServletResponse response) throws IOException {   	
    	String header = request.getHeader("Authorization");
    	String message = null;
    	if(header == null) {
    		message = "Not Valid REquest";
    		response.getWriter().write(message);
    		response.sendRedirect("http://localhost:4200/token?eid=eid"); //JWT TOKEN
    	}else {
    		message = "Hi Pogie";
    	}
        return message;
    }
    
    @PostMapping("/form-data")
    public String formData(@RequestBody String name) {
    	String message = null;
    	if( name == "arnel") {
    		message = "You are pogie "+ name;
    	}
    	return message;
    }
}

