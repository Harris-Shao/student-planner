package edu.ecu.seng6240.team6.Helper;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String getDataString(HttpServletRequest request) throws IOException
	{
		StringBuilder sb = new StringBuilder();
	    BufferedReader br = request.getReader();
	    String str;
	    while( (str = br.readLine()) != null ){
	        sb.append(str);
	    }
	    if (sb.length() > 0) return sb.toString();
		return null; 		
	}

	
}
