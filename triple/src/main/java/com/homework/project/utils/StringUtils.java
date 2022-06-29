package com.homework.project.utils;

import java.util.ArrayList;

public class StringUtils {
	
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isNotNullOrNotEmpty(String str) {
        if (str == null) {
            return false;
        } else if (str.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static String objectToString(Object objectStr) {
    	
    	return objectStr == null ? "" : String.valueOf(objectStr);
    	
    }

    
    public static String arrayToCommaString(ArrayList<String> arrStr) {
    	
    	StringBuilder buildStr = new StringBuilder();
    	
    	if (arrStr != null) {
    		
    		int count = 0;
    		
    		for(String str : arrStr) {
    			
    			buildStr.append(str);
    			
    			if(count < arrStr.size()-1 ) {
    				
    				buildStr.append(",");
    				
    			}
    			
    			count++;
    			
    			
    		}
    		
    	}
    	return buildStr.toString();
    	
    }

}
