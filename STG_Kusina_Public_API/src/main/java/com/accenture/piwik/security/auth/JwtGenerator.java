package com.accenture.piwik.security.auth;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.piwik.security.model.JwtPiwikUser;
import com.accenture.piwik.security.model.JwtUser;
import com.accenture.piwik.utils.JwtConfigUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	@Autowired
	private JwtConfigUtils jwtConfigUtils;

    public String generate(JwtPiwikUser jwtPiwikUser) {

    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    	
    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfigUtils.getJwtSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims()
                .setSubject(jwtPiwikUser.getEid())
                .setAudience("Piwik Onboarded Applications")
                .setIssuer("Infrastructure Metrics")
                .setIssuedAt(new Date());
         		
        claims.put("airid", jwtPiwikUser.getAirid());
        claims.put("id", jwtPiwikUser.getId());
        claims.put("access", jwtPiwikUser.getAccess());
        claims.put("expiry", jwtPiwikUser.getExpiryDate());
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}

