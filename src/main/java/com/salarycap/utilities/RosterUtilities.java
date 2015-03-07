package com.salarycap.utilities;

import org.apache.log4j.Logger;

public class RosterUtilities {
	
	private static final Logger logger = Logger.getLogger(RosterUtilities.class);
	
	public static Integer getFreeAgentYearAsInt(String freeAgentString) {
		try{
			Integer freeAgentYear = Integer.parseInt(freeAgentString.substring(0, 4));
			return freeAgentYear;
		}
		catch(StringIndexOutOfBoundsException | NullPointerException | NumberFormatException e){
			logger.info("Error attempting get free agent year out of the string: " + freeAgentString);
			return 0;
		}
	}
}
