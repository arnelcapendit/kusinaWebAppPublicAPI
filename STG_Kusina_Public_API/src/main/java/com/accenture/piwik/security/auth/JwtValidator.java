package com.accenture.piwik.security.auth;

import com.accenture.piwik.security.model.JwtPiwikAdalUser;
import com.accenture.piwik.security.model.JwtPiwikUser;
import com.accenture.piwik.security.model.JwtUser;
import com.accenture.piwik.utils.JwtConfigUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

	@Autowired
	private JwtConfigUtils jwtConfigUtils;
  
    public JwtPiwikUser validate(String token) {

    	JwtPiwikUser jwtPiwikUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtConfigUtils.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody();

            jwtPiwikUser = new JwtPiwikUser();

            jwtPiwikUser.setEid(body.getSubject());
            jwtPiwikUser.setId(body.get("id").toString());
        	jwtPiwikUser.setAirid(body.get("airid").toString());
        	jwtPiwikUser.setAccess(body.get("access").toString());
        	jwtPiwikUser.setExpiryDate(body.get("expiry").toString());
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtPiwikUser;
    }
    
    
    public JwtPiwikAdalUser validateAdalToken(String token) {

    	JwtPiwikAdalUser jwtPiwikAdalUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("3817.piwik.kusina.beta.web"))
                    .parseClaimsJws(token)
                    .getBody();

            jwtPiwikAdalUser = new JwtPiwikAdalUser();
            jwtPiwikAdalUser.setEmail(body.getSubject());
            jwtPiwikAdalUser.setAud(body.getAudience());
            System.out.println("This is subject token: "+ body.getSubject());
            
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtPiwikAdalUser;
    }
}
