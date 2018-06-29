package com.accenture.piwik.utils;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class KusinaPayloadUtils {
	
	
	public String generatePayload(JSONObject obj) {
		
		String param = obj.get("payload").toString();
		
		switch(param) {
			case "piwik": 
				return retrieveAllData(obj);
				
			case "piwikUser":
				return retrieveUserData(obj);
			
			default:
				break;
		}	
		return null;
	}
	
	public String retrieveAllData(JSONObject obj) {
		String id = obj.get("objectId").toString();
		
		String payload = "{\r\n" + 
				"  \"size\": 1000, \r\n" + 
				"  \"sort\": [\r\n" + 
				"    {\r\n" + 
				"      \"TIMESTAMP\": {\r\n" + 
				"        \"order\": \"desc\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"query\": {\r\n" + 
				"    \"bool\": {\r\n" + 
				"      \"must\": [\r\n" + 
				"        {\r\n" + 
				"          \"terms\": {\r\n" + 
				"            \"ID\": [\""+id+"\"]\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return payload;
	}

	public String retrieveUserData(JSONObject obj) {
		String eid = obj.get("objectEid").toString();
		
		String payload = "{\r\n" + 
				"  \"query\": {\r\n" + 
				"    \"match\": {\r\n" + 
				"      \"user_eid\": \""+eid+"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return payload;
	}

}
