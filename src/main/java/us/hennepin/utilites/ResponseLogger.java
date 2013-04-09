package us.hennepin.utilites;

import org.apache.tapestry5.services.Response;

public class ResponseLogger {

	public static void logResponse(Response response){
		logPrintWriter(response);
		logOutputStream(response);
	}

	private static void logOutputStream(Response response) {
		
	}

	private static void logPrintWriter(Response response) {
		
		response.setHeader("Expires", new Long(System.currentTimeMillis()-10000000).toString());
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");		
	}
	
	

}
