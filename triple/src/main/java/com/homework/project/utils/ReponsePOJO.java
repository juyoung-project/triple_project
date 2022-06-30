package com.homework.project.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReponsePOJO {
	
	/**
	 * Return 되는 값을 통일 시키기위한 POJO Class
	 * 
	 * */
	
	private int returnCode = 0;
	private String returnMessage = "success";
	
	Object appData = null;
	
	public ReponsePOJO(Object data) {this.appData = data;}
	
	public static ReponsePOJO success( Object data, String returnMessage ) {
		return buildMessage(200, returnMessage, data);
	}
	
	public static ReponsePOJO error( Object data, String returnMessage ) {
		return buildMessage(500, returnMessage, data);
	}
	
	
	private static ReponsePOJO buildMessage(int returnCode, String returnMessage, Object data) {
		
		ReponsePOJO rtn = new ReponsePOJO(data);
		
		rtn.setReturnCode(returnCode);
		
		if(StringUtils.isNotNullOrNotEmpty(returnMessage)) {
			rtn.returnMessage = returnMessage;
		}
		
		return rtn;
		
	}
	
	
}
