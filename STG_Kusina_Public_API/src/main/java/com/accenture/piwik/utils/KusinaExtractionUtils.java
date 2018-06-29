package com.accenture.piwik.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.piwik.model.Piwik;
import com.accenture.piwik.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component
public class KusinaExtractionUtils {
	
	@Autowired
	private KusinaDateUtils kusinaDateUtils;
	
	@Autowired
	private KusinaStringUtils kusinaStringUtils;
	
	public JSONObject getAggregationDocument(String response) throws org.json.simple.parser.ParseException {

        JSONParser parser = new JSONParser();
        JSONObject mainObj = (JSONObject) parser.parse(response);

        return mainObj;
    }
	
	public List<Piwik> retrieveAllData(JSONObject o) throws ParseException {
    	ObjectMapper mapper = new ObjectMapper();
        JSONObject hitsObj = (JSONObject) o.get("hits");
        JSONArray innerHits = (JSONArray) hitsObj.get("hits");
        List<Piwik> pwkl = new ArrayList<>();
        
        Piwik pwk;
           
        for (Object obj : innerHits) {
            JSONObject oj = (JSONObject) obj;
            //doc.add(o.get("_source"));
            String _id = (String) oj.get("_id");
            JSONObject _sourceObj = (JSONObject) oj.get("_source");
            
            pwk = new Piwik();
            pwk.setAIRID(kusinaStringUtils.getModStr(_sourceObj.get("AIRID")));
            pwk.setID(kusinaStringUtils.getModStr(_sourceObj.get("ID")));
            pwk.setAPPNAME(kusinaStringUtils.getModStr(_sourceObj.get("APPNAME")));
            pwk.setTIMESTAMP(kusinaStringUtils.getModStr(_sourceObj.get("TIMESTAMP")));
            pwk.setLastActionTime(kusinaStringUtils.getModStr(_sourceObj.get("LastActionTime")));
            pwk.setFirstActionTime(kusinaStringUtils.getModStr(_sourceObj.get("FirstActionTime")));
            pwk.setLocalTime(kusinaStringUtils.getModStr(_sourceObj.get("LocalTime")));
            pwk.setAddress(kusinaStringUtils.getModStr(_sourceObj.get("Address")));
            pwk.setUser(kusinaStringUtils.getModStr(_sourceObj.get("User")));
            pwk.setHits(kusinaStringUtils.getModStr(_sourceObj.get("Hits")));
            pwk.setVisits(kusinaStringUtils.getModStr(_sourceObj.get("Address")));
            pwk.setGeography(kusinaStringUtils.getModStr(_sourceObj.get("Geography")));
            pwk.setCountry(kusinaStringUtils.getModStr(_sourceObj.get("Country")));
            pwk.setCareerLevel(kusinaStringUtils.getModStr(_sourceObj.get("CareerLevel")));
            pwk.setCareerTracks(kusinaStringUtils.getModStr(_sourceObj.get("CareerTracks")));
            pwk.setActionURLType((kusinaStringUtils.getModStr(_sourceObj.get("ActionURLType"))));
            pwk.setActionNameType((kusinaStringUtils.getModStr(_sourceObj.get("ActionNameType"))));
            pwk.setEventAction(kusinaStringUtils.getModStr(_sourceObj.get("EventAction")));
            pwk.setEventCategory(kusinaStringUtils.getModStr(_sourceObj.get("EventCategory")));
            pwk.setEventName(kusinaStringUtils.getModStr(_sourceObj.get("EventName")));
            pwk.setEventValue((kusinaStringUtils.getModStr(_sourceObj.get("EventValue"))));
            pwk.setPageTitle(kusinaStringUtils.getModStr(_sourceObj.get("PageTitle")));
            pwk.setSearchKeyword((kusinaStringUtils.getModStr(_sourceObj.get("SearchKeyword"))));
            pwk.setPageOutlink(kusinaStringUtils.getModStr(_sourceObj.get("PageOutlink")));
            pwk.setDownload(kusinaStringUtils.getModStr(_sourceObj.get("Download")));
            pwk.setPageURL(kusinaStringUtils.getModStr(_sourceObj.get("PageURL")));
            pwk.setCleanPageURL(kusinaStringUtils.getModStr(_sourceObj.get("CleanPageURL")));
            pwk.setRefererType(kusinaStringUtils.getModStr(_sourceObj.get("RefererType")));
            pwk.setRefererURL(kusinaStringUtils.getModStr(_sourceObj.get("RefererURL")));
            pwk.setRefererKeyword(kusinaStringUtils.getModStr(_sourceObj.get("RefererKeyword")));
            pwk.setRefererName(kusinaStringUtils.getModStr(_sourceObj.get("Address")));
            pwk.setPageCustomVariable1Value(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable1Value")));
            pwk.setPageCustomVariable2Value(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable2Value")));
            pwk.setPageCustomVariable3Value(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable3Value")));
            pwk.setPageCustomVariable4Value(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable4Value")));
            pwk.setPageCustomVariable5Value(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable5Value")));
            pwk.setPageCustomVariable1Name(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable1Name")));
            pwk.setPageCustomVariable2Name(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable2Name")));
            pwk.setPageCustomVariable3Name(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable3Name")));
            pwk.setPageCustomVariable4Name(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable4Name")));
            pwk.setPageCustomVariable5Name(kusinaStringUtils.getModStr(_sourceObj.get("PageCustomVariable5Name")));
            pwk.setVisitCustomVariable1Value(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable1Value")));
            pwk.setVisitCustomVariable2Value(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable2Value")));
            pwk.setVisitCustomVariable3Value(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable3Value")));
            pwk.setVisitCustomVariable4Value(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable4Value")));
            pwk.setVisitCustomVariable5Value(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable5Value")));
            pwk.setVisitCustomVariable1Name(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable1Name")));
            pwk.setVisitCustomVariable2Name(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable2Name")));
            pwk.setVisitCustomVariable3Name(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable3Name")));
            pwk.setVisitCustomVariable4Name(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable4Name")));
            pwk.setVisitCustomVariable5Name(kusinaStringUtils.getModStr(_sourceObj.get("VisitCustomVariable5Name")));
            pwk.setGenerationTime((kusinaStringUtils.getModStr(_sourceObj.get("GenerationTime"))));
            pwk.setBounceCount((kusinaStringUtils.getModStr(_sourceObj.get("BounceCount"))));
            pwk.setSumTimeSpent((kusinaStringUtils.getModStr(_sourceObj.get("SumTimeSpent"))));
            pwk.setBrowser(kusinaStringUtils.getModStr(_sourceObj.get("Browser")));
            pwk.setOperatingSystem(kusinaStringUtils.getModStr(_sourceObj.get("OperatingSystem")));
            pwk.setDeviceType(kusinaStringUtils.getModStr(_sourceObj.get("DeviceType")));
            pwk.setDeviceBrand(kusinaStringUtils.getModStr(_sourceObj.get("DeviceBrand")));
            pwk.setDeviceModel(kusinaStringUtils.getModStr(_sourceObj.get("DeviceModel")));
            pwk.setBrowserVersion(kusinaStringUtils.getModStr(_sourceObj.get("BrowserVersion")));
            pwk.setBrowserEngine(kusinaStringUtils.getModStr(_sourceObj.get("BrowserEngine")));
            pwk.setDeviceOperatingSystemVersion(kusinaStringUtils.getModStr(_sourceObj.get("DeviceOperatingSystemVersion")));
            pwk.setResolutions(kusinaStringUtils.getModStr(_sourceObj.get("Resolutions")));
            pwk.setRegion(kusinaStringUtils.getModStr(_sourceObj.get("Region")));
            pwk.setBrowserLanguage(kusinaStringUtils.getModStr(_sourceObj.get("BrowserLanguage")));
            pwk.setCity(kusinaStringUtils.getModStr(_sourceObj.get("City")));
            pwk.setLocationProvider(kusinaStringUtils.getModStr(_sourceObj.get("LocationProvider")));
            pwk.setQuickTimePlugin((kusinaStringUtils.getModStr(_sourceObj.get("QuickTimePlugin"))));
            pwk.setPDFPlugin((kusinaStringUtils.getModStr(_sourceObj.get("PDFPlugin"))));
            pwk.setRealPlayerPlugin((kusinaStringUtils.getModStr(_sourceObj.get("RealPlayerPlugin"))));
            pwk.setSilverlightPlugin((kusinaStringUtils.getModStr(_sourceObj.get("SilverlightPlugin"))));
            pwk.setWindowsMediaPlugin((kusinaStringUtils.getModStr(_sourceObj.get("WindowsMediaPlugin"))));
            pwk.setJavaPlugin((kusinaStringUtils.getModStr(_sourceObj.get("JavaPlugin"))));
            pwk.setGearPlugin((kusinaStringUtils.getModStr(_sourceObj.get("GearPlugin"))));
            pwk.setCookieEnabled((kusinaStringUtils.getModStr(_sourceObj.get("CookieEnabled"))));
            pwk.setDirectorPlugin((kusinaStringUtils.getModStr(_sourceObj.get("DirectorPlugin"))));
            pwk.setFlashPlugin((kusinaStringUtils.getModStr(_sourceObj.get("FlashPlugin"))));
            pwk.setReturningVisitor((kusinaStringUtils.getModStr(_sourceObj.get("ReturningVisitor"))));
            pwk.setTotalSearches((kusinaStringUtils.getModStr(_sourceObj.get("TotalSearches"))));
            pwk.setTotalEvents((kusinaStringUtils.getModStr(_sourceObj.get("TotalEvents"))));
            pwk.setDaysCountSinceVisitorLastVisit((kusinaStringUtils.getModStr(_sourceObj.get("DaysCountSinceVisitorLastVisit"))));
            pwk.setVisitsCountTheVisitorMadeUpToThisOne((kusinaStringUtils.getModStr(_sourceObj.get("VisitsCountTheVisitorMadeUpToThisOne"))));
            pwk.setDateTimeOfLastAction((kusinaStringUtils.getModStr(_sourceObj.get("DateTimeOfLastAction"))));
            pwk.setTotalElapseTimeOfVisit((kusinaStringUtils.getModStr(_sourceObj.get("TotalElapseTimeOfVisit"))));
            pwk.setIDActionURLRef((kusinaStringUtils.getModStr(_sourceObj.get("IDActionURLRef"))));
            pwk.setIDActionNameRef((kusinaStringUtils.getModStr(_sourceObj.get("IDActionNameRef"))));
            pwk.setIDActionURL((kusinaStringUtils.getModStr(_sourceObj.get("IDActionURL"))));
            pwk.setIDActionName((kusinaStringUtils.getModStr(_sourceObj.get("IDActionName"))));
            pwk.setVisitExitIDActionURL((kusinaStringUtils.getModStr(_sourceObj.get("VisitExitIDActionURL"))));
            pwk.setVisitExitIDActionName((kusinaStringUtils.getModStr(_sourceObj.get("VisitExitIDActionName"))));
            pwk.setVisitEntryIDActionName((kusinaStringUtils.getModStr(_sourceObj.get("VisitEntryIDActionName"))));
            pwk.setVisitEntryIDActionURL((kusinaStringUtils.getModStr(_sourceObj.get("VisitEntryIDActionURL"))));
            pwk.setVisitorDaysSinceOrder((kusinaStringUtils.getModStr(_sourceObj.get("VisitorDaysSinceOrder"))));
            pwk.setCareerLevelDescription(kusinaStringUtils.getModStr(_sourceObj.get("CareerLevelDescription")));
            pwk.setCareerTrackDescription(kusinaStringUtils.getModStr(_sourceObj.get("CareerTrackDescription")));
            pwk.setGeographicalUnit(kusinaStringUtils.getModStr(_sourceObj.get("GeographicalUnit")));
            
            pwkl.add(pwk);
        }
       
        try {
            String finStr = mapper.writeValueAsString(pwkl);
            JSONArray convertedObj = new Gson().fromJson(finStr, JSONArray.class);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(KusinaExtractionUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pwkl;
    }	
	
	public User retrieveUserData(JSONObject o) throws ParseException {
    	ObjectMapper mapper = new ObjectMapper();
        JSONObject hitsObj = (JSONObject) o.get("hits");
        JSONArray innerHits = (JSONArray) hitsObj.get("hits");

        User user = null;
        for (Object obj : innerHits) {
            JSONObject oj = (JSONObject) obj;
            //doc.add(o.get("_source"));
            String _id = (String) oj.get("_id");
            JSONObject _sourceObj = (JSONObject) oj.get("_source");
            
            user = new User();
            user.setUniqueId(_id);
            user.setEid(_sourceObj.get("user_eid").toString());
            user.setAccess(_sourceObj.get("user_access").toString());
            user.setId(_sourceObj.get("user_id").toString().replace("\"", "").replace("[", "").replace("]", ""));
            user.setAirid(_sourceObj.get("user_airid").toString().replace("\"", "").replace("[", "").replace("]", ""));
            user.setExpiryDate(_sourceObj.get("access_expiration_date").toString());
            user.setStatus(_sourceObj.get("access_status").toString());
            user.setCreatedDate(_sourceObj.get("created_date").toString());
            user.setLastUpdatedBy(_sourceObj.get("last_update_by").toString());
            user.setLastUpdatedDate(_sourceObj.get("last_update_date").toString()); 
         
        }
        return user;
    }	

}
