package com.app.menuFunction;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class AcquireInput {
	
	private static Logger log = Logger.getLogger(AcquireInput.class);
	
	public static int intInput(Scanner sc) {
		int result = 0;
		try {
			result = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			PrintMessage.invalidInputMessage(log);
			log.info("Please only enter numbers. For example: 1");
			e.printStackTrace();
		}
		
		//record the user input
		log.trace(result+"(Logged)");
		
		return result;
	}
	
	public static long longInput(Scanner sc) {
		long result = 0;
		try {
			result = sc.nextLong();
			sc.nextLine();
		} catch (Exception e) {
			PrintMessage.invalidInputMessage(log);
			log.info("Please only enter numbers. For example: 2345612");
			e.printStackTrace();
		}
		
		//record the user input
		log.trace(result+"(Logged)");
		
		return result;
	}
	
	public static double doubleInput(Scanner sc) {
		 double result = 0;
		try {
			result = sc.nextDouble();
			sc.nextLine();
		} catch (Exception e) {
			PrintMessage.invalidInputMessage(log);
			log.info("Please only enter numbers and one dot. For example: 234.5612");
			e.printStackTrace();
		}
		
		//record the user input
		log.trace(result);
		
		return result;
	}
	
	public static String stringInput(Scanner sc) {
		 String result = "";
		try {
			result = sc.nextLine();
			
		} catch (Exception e) {
			PrintMessage.invalidInputMessage(log);
			log.info("Please enter at least one charactor. For example: 1");
			e.printStackTrace();
		}
		
		//record the user input
		log.trace(result+"(Logged)");
		
		return result;
	}
}
