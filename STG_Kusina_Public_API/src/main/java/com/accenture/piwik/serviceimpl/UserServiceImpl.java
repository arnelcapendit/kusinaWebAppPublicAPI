package com.accenture.piwik.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.piwik.model.Piwik;
import com.accenture.piwik.model.User;
import com.accenture.piwik.service.UserService;
import com.accenture.piwik.utils.KusinaConfigUtils;
import com.accenture.piwik.utils.KusinaElasticUtils;
import com.accenture.piwik.utils.KusinaExtractionUtils;

@Service
public class UserServiceImpl implements UserService {

	private static final String PIWIK_USERS = "/users/_search?pretty=true";
	
	@Autowired
	private KusinaConfigUtils kusinaConfigUtils;
	
	@Autowired
	private KusinaExtractionUtils kusinaExtractionUtils;
	
	@Autowired
	private KusinaElasticUtils kusinaElasticUtils;
	
	@Override
	public User retrieveUserData(String eid) {
		
		User user = null; 
        try {

            JSONObject o = new JSONObject();
            o.put("method", "GET");

            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(kusinaConfigUtils.getPiwikUsers());
            sb.append(PIWIK_USERS);

            o.put("uri", sb.toString());
            o.put("hasPayload", true);
            o.put("isKusina", false);
            o.put("payload", "piwikUser");
            o.put("objectEid", eid);

            user = new User();
            user = kusinaExtractionUtils.retrieveUserData(kusinaElasticUtils.sendRequest(o));

        } catch (Exception ex) {
            Logger.getLogger(PiwikServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
		
	}

	
	
}
