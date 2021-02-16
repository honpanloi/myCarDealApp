package com.app.menuFunction;

import org.apache.log4j.Logger;

public class PrintMessage {
	public static void optionNotAvailable(Logger log) {
		log.info("Option entered is not available.");
		log.info("Please enter a number to select an option to move forward.");
	}
	public static void spaceOutTheOldMessages(Logger log) {
		//couple of new lines
		log.info("\n\n\n");
	}
	public static void invalidInputMessage(Logger log) {
		log.info("Your input could not be accepted. Please try again.");
	}
}
