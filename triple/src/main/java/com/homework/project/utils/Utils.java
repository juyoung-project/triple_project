package com.homework.project.utils;

import java.util.UUID;

public class Utils {
	
	public static String getUUID() {
		
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();		
	}
	
}
