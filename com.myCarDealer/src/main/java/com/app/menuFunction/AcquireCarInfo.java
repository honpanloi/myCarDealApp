package com.app.menuFunction;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.menu.HomeMenu;
import com.app.service.CustomerCrudService;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.util.Validation;

public class AcquireCarInfo {
	
	private static Logger log = Logger.getLogger(HomeMenu.class);
	static CustomerCrudService customerCrudService;
	
	public static long getCustomerIdByEmail() {
		return 0;
		
	}

	public static long getCustomerIdByEmail(Scanner sc) {
		long customer_id = 0l;

		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Whose the owner of this car? Please enter the email address of the customer.");
			String email = null;
			email = AcquireInput.stringInput(sc);
			customerCrudService = new CustomerCrudServiceImpl();
			try {
				customer_id = customerCrudService.getCustomerIdByEmail(email);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (customer_id == 0) {
				log.info("Unable to find a customer by that email. Please try again.");
				continue;
			} 
		} while (customer_id==0);
		
		return customer_id;
	}
	
	public static String manufacturer(Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("Whose the manufacturer of this car?");
		String manufacturer = null;
		manufacturer = AcquireInput.stringInput(sc);

		return manufacturer;
	}
	
	public static String model(Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("What is the model of this car?");
		String model = null;
		model = AcquireInput.stringInput(sc);

		return model;
	}
	
	public static int yearMade(Scanner sc) {
		String year = null;
		boolean isValidYear = false;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("What is the year of this car?");
			year = AcquireInput.stringInput(sc);
			
			if (!Validation.isValidYearMade(year)) {
				log.info("This is not a valid year of a car.");
				continue;
			} 
			
			if(Integer.parseInt(year)<LocalDate.now().getYear()-30) {
				log.info("The car is too old for us to take.");
				continue;
			}
			
			isValidYear = true;
			
		} while (!isValidYear);
		
		return Integer.parseInt(year);
	}
	
	public static int mileage(Scanner sc) {
		String mileageEntered = null;
		boolean isValidMileage = false;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("What is the mileage on this car?");
			mileageEntered = AcquireInput.stringInput(sc);
			
			try {
				Integer.parseInt(mileageEntered);
			} catch (Exception e) {
				log.info("This is not a valid mileage");
				continue;
			}
			
			if (Integer.parseInt(mileageEntered)<0) {
				log.info("Please enter a number that is greater than 0.");
				continue;
			} 
			
			isValidMileage = true;
			
		} while (!isValidMileage);
		
		return Integer.parseInt(mileageEntered);
	}

	public static String fuelType(Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("What is the fuel type of this car?");
		String fuelType = null;
		fuelType = AcquireInput.stringInput(sc);

		return fuelType;
	}
	
	public static String ex_color(Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("What is the external color of this car?");
		String ex_color = null;
		ex_color = AcquireInput.stringInput(sc);

		return ex_color;
	}
	
	public static String in_color(Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("What is the internal color of this car?");
		String in_color = null;
		in_color = AcquireInput.stringInput(sc);

		return in_color;
	}
	
	public static String park_location(Scanner sc) {
		String park_location = null;
		boolean isValidPark_location = false;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Where is the car parked?");
			park_location = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidParking(park_location)) {
				log.info("This is not a valid parking location.");
				continue;
			}
			
			isValidPark_location = true;
			
		} while (!isValidPark_location);
		
		
		return park_location;
	}
	
	public static double offer_desired(Scanner sc) {
		double offer_desired = 0;
		boolean isValidOffer_desired = false;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("What is the desired offer for this car? You can put zero if you want an evaluation from us.");
			offer_desired = AcquireInput.doubleInput(sc);
			
			if(offer_desired<1000d) {
				log.info("We cannot take a car that is is lower than $1000.");
				continue;
			}
			
			isValidOffer_desired = true;
			
		} while (!isValidOffer_desired);
		
		
		return offer_desired;
	}
}
