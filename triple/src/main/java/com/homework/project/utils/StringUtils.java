package com.homework.project.utils;

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

}
