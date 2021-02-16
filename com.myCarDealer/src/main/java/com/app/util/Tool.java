package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Tool {
	
	public static String getPrintedCurrentDate() {
		
		Date currentDate = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd EEEE z Z HH:mm:ss");
		
		return format1.format(currentDate).toString();
	}
	
	public static Date getCurrentDate() {

		Date currentDate = new Date();
		return currentDate;
	}
	
	public static void get2SecondProcessingTime(Logger log) {
		
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
				log.info("Processing...");
				log.info("...");
				log.info("...");
				log.info("...");
			} catch (InterruptedException e) {
				log.info("Internal error occurred. Please exit the app.");
			} 
		}
	}
	

}
