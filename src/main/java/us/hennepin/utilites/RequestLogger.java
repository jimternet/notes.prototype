package us.hennepin.utilites;

import java.util.List;

import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

	private static void logParameterNames(Request request) {

		List<String> names = request.getParameterNames();
		logger.info("---------------------------------");
		for (String name : names) {
			String parameter = request.getParameter(name);
			logger.info(name + " : " + parameter);
		}
		logger.info("---------------------------------");
	}

	private static void logHeaderNames(Request request) {

		List<String> names = request.getHeaderNames();

		request.getHeaderNames();

		logger.info("----------HEADERS----------------");
		for (String name : names) {
			String header = request.getHeader(name);
			logger.info(name + " : " + header);

		}
		logger.info("----------END HEADERS ----------------------");
	}
	
	public static void logRequest(Request request){
		logParameterNames(request);
		logHeaderNames(request);
	}

}
