package com.accenture.piwik.serviceimpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.piwik.model.Piwik;
import com.accenture.piwik.service.PiwikService;
import com.accenture.piwik.utils.KusinaConfigUtils;
import com.accenture.piwik.utils.KusinaElasticUtils;
import com.accenture.piwik.utils.KusinaExtractionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PiwikServiceImpl implements PiwikService{
	
	private static final String WEBMETRIC_PIWIK = "/piwik/_search?pretty=true";
	private static final String KUSINA_USERS = "/users/_search?pretty=true";
	
	@Autowired
	private KusinaConfigUtils kusinaConfigUtils;
	
	@Autowired
	private KusinaExtractionUtils kusinaExtractionUtils;
	
	@Autowired
	private KusinaElasticUtils kusinaElasticUtils;
	
	
	@Override
	public List<Piwik> retrieveAllData(String id) {
		
		 List<Piwik> piwikApp = new ArrayList<>();
	        try {

	            JSONObject o = new JSONObject();
	            o.put("method", "GET");

	            StringBuilder sb = new StringBuilder();
	            sb.append("/");
	            sb.append(kusinaConfigUtils.getPiwikIndex());
	            sb.append(WEBMETRIC_PIWIK);

	            o.put("uri", sb.toString());
	            o.put("hasPayload", true);
	            o.put("isKusina", false);
	            o.put("payload", "piwik");
	            o.put("objectId", id);

	            piwikApp = kusinaExtractionUtils.retrieveAllData(kusinaElasticUtils.sendRequest(o));

	        } catch (Exception ex) {
	            Logger.getLogger(PiwikServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return piwikApp;
	}
	

}
