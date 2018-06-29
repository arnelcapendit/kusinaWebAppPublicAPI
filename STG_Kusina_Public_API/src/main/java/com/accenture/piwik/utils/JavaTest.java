package com.accenture.piwik.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;

public class JavaTest {

	public static void main(String[] args) throws ParseException {
		String testDate = "1546185600000";
		System.out.println(convertEpochToDate(testDate));
		System.out.println(convertStrDateToMillis(testDate));
		System.out.println(convertStrDateToEpochMilli(testDate));
		
	}

	 public static String convertEpochToDate(String date) {
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	    	//sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    	return sdf.format(new Date(Long.parseLong(date)));
	    }
	
	public static long convertStrDateToMillis(String date) {

        return new DateTime(Long.parseLong(date)).getMillis();
    }
	
	public static Long convertStrDateToEpochMilli(String strDate) {

        try {
            return Long.parseLong(strDate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return new DateTime(strDate).getMillis();

    }
	
	
}
