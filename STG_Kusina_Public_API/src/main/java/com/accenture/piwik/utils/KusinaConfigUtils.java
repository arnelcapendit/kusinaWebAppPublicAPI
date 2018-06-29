package com.accenture.piwik.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KusinaConfigUtils {
		
	@Value("${host.url}")
    private String appClientHost;
	
	@Value("${host.port}")
    private String appClientPort;
	
	@Value("${host.env}")
    private String appClientEnvironment;
	
	@Value("${app.piwik.index}")
	private String piwikIndex;
	
	@Value("${app.piwik.users.index}")
	private String piwikUsers;
	
	public String getPiwikIndex() {
		return this.piwikIndex;
	}
	
	public String getAppClientPort() {
		return this.appClientPort;
	}
	
	public String getAppClientEnvironment() {
		return this.appClientEnvironment;
	}
	
	public String getAppClientHost() {
		return this.appClientHost;
	}
	
	public String getPiwikUsers() {
		return this.piwikUsers;
	}
	
	public String generateURI(String uri) {

        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(appClientHost);
        sb.append(":");
        sb.append(appClientPort);
        sb.append(uri);

        return sb.toString();
    }

    public String getEnvironmentCredentials() {

        if (appClientEnvironment.equalsIgnoreCase("STG")) {
            return "elastic:Pa55w0rdELKSTG";
        }
        return "elastic:3La5Ti2Sta6K";
    }

}
