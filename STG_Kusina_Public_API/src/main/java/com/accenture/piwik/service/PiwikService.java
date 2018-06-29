package com.accenture.piwik.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.accenture.piwik.model.Piwik;

public interface PiwikService {
	
	public List<Piwik> retrieveAllData(String id);

}
