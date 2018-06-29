package com.accenture.piwik.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class KusinaElasticUtils {

	@Autowired
    private KusinaConfigUtils kusinaConfigUtils;

    @Autowired
    private KusinaPayloadUtils kusinaPayloadUtils;

    @Autowired
    private KusinaExtractionUtils kusinaExtractionUtils;

    @Autowired
    private KusinaValidationUtils kusinaValidationUtils;
	
	public JSONObject sendRequest(JSONObject o) throws MalformedURLException, ProtocolException, IOException, ParseException, org.json.simple.parser.ParseException {

        String url = kusinaConfigUtils.generateURI(o.get("uri").toString());

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        System.out.println("URI: " + url);
		
        con.setRequestMethod(o.get("method").toString());      
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        
        if (!StringUtils.isEmpty(kusinaConfigUtils.getAppClientEnvironment())) {
            String loginUsernamePassword = kusinaConfigUtils.getEnvironmentCredentials();
            con.setRequestProperty("Authorization", "Basic " + new String(Base64.getEncoder().encode(loginUsernamePassword.getBytes())));
        }

        if ((boolean) o.get("hasPayload")) {
            String payload = kusinaPayloadUtils.generatePayload(o);
            System.out.println("PAYLOAD: " + payload);
            con.setDoOutput(true);
            try (DataOutputStream output = new DataOutputStream(con.getOutputStream())) {
                output.writeBytes(payload);
                int bytesWritten = output.size();
                System.out.println("Total " + bytesWritten + " bytes are written to stream.");
                output.flush();
            }
            
        }
        
    
        int responseCode = con.getResponseCode();
        System.out.println("\nSending '" + o.get("method").toString() + "' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        StringBuilder response = null;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
        // Parsing request into JSON
        JSONObject result = new JSONObject();
        if ((boolean) o.get("isKusina")) {
          
        } else {
        	 result = kusinaExtractionUtils.getAggregationDocument(response.toString());
        }

        con.disconnect();
        return result;

    }
}
