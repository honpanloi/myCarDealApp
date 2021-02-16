package com.app.menuFunction;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.service.CustomerCrudService;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.util.Validation;

public class AcquireRegistrationInfo {

	static CustomerCrudService customerCrudService;
	private static Logger log = Logger.getLogger(AcquireRegistrationInfo.class);
	
	public static String userName(Scanner sc) {
		String userNameEntered = null;
		boolean isInvalidUserName = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please create your user name. It has to be at least 6 characters and at most 20.");
			log.info("User name cannot contain spaces or special characters.");
			
			userNameEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidUserName(userNameEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("The user name you entered cannot be accepted. Please try again.\n");
				
				continue;
			}
			
			customerCrudService = new CustomerCrudServiceImpl();
			boolean isTaken = true;
			try {
				isTaken = customerCrudService.isLoginUserNameTaken(userNameEntered);
				
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}
			
			if(isTaken) {
				log.info(userNameEntered+" is already taken. Please try another one.");
				continue;
			}
			
			log.info("User name accepted!");
			isInvalidUserName = false;
			
			
		} while (isInvalidUserName);
		
		return userNameEntered;
	}

	public static String password(Scanner sc) {
		String passwordEntered = null;
		boolean isInvalidPassword = true;
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please create your user password. It has to be at least 8 characters and at most 20.");
			log.info("It also needs to contain at least 1 number, 1 upper case letter, and 1 lower case letter.");
			
			passwordEntered = AcquireInput.stringInput(sc);
			
			if(Validation.isValidPassword(passwordEntered)) {
				log.info("Password accepted!");
				isInvalidPassword = false;
			}else {
				log.info("The password you entered cannot be accepted. Please try again.\n");
			}
			
		} while (isInvalidPassword);
		
		return passwordEntered;
	}

	public static int ssn(Scanner sc, boolean isEmployee) {
		String ssnEntered = null;
		boolean isInvalidSsn = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" socail security number using the following format.");
			log.info("111-11-1111");
			
			ssnEntered = AcquireInput.stringInput(sc);
			ssnEntered = ssnEntered.replaceAll("\\D", "");
			
			if(!Validation.isValidSocialSecurityNumber(ssnEntered)) {
				log.info("The ssn you entered cannot be accepted. Please try again.");
				continue;
			}
			
			//check if ssn is registered
			customerCrudService = new CustomerCrudServiceImpl();
			try {
				if(customerCrudService.isSsnTaken(Integer.parseInt(ssnEntered))) {
					log.info("Social security number entered is already registered.");
					continue;
				}
			} catch (NumberFormatException | BusinessException e) {
				log.info(e.getMessage());
			}
			
			log.info("Social security number entered is accepted!");
			isInvalidSsn = false;
			
		} while (isInvalidSsn);
		
		return Integer.parseInt(ssnEntered);
	}

	public static String driversLicence(Scanner sc, boolean isEmployee) {
		String dlEntered = null;
		boolean isInvalidDL = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" driver's licence.");
			
			dlEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidDriversLience(dlEntered)) {
				log.info("Driver's licence entered is invalid. Please try again.");
				continue;
			}
			
			//check if dl is registered in the system already
			customerCrudService = new CustomerCrudServiceImpl();
			try {
				if(customerCrudService.isDriversLicenceTaken(dlEntered)) {
					log.info("Driver's licence entered is already registered.");
					continue;
				}
			} catch (NumberFormatException | BusinessException e) {
				log.info(e.getMessage());
			}
			
			log.info("Driver's licence entered is accepted!");
			isInvalidDL = false;
			
		} while (isInvalidDL);
		
		
		return dlEntered;
	}

	public static String firstOrLastName(Scanner sc, boolean isEmployee, boolean IsFirstName) {
		String nameEntered = null;
		boolean isInvalidName = true;
		String nameType = "last";
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			
			if(IsFirstName) {
				nameType = "first";
			}
			
			log.info("Please enter "+answeringTarget(isEmployee)+" "+nameType+" name.");
			
			nameEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidName(nameEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("Name entered cannot be unaccepted.");
				log.info("It has to be at least 2 characters and at most 20.");
				log.info("It cannot contain special characters.");
				continue;
			}
			
			log.info("Name accepted!");
			isInvalidName = false;
			
		} while (isInvalidName);
		
		return nameEntered;
	}

	public static String salutation(Scanner sc, boolean isEmployee) {
		String salutation = null;
	
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("Please pick "+answeringTarget(isEmployee)+" salutation.\n");
		log.info("1) Mr.");
		log.info("2) Mrs.");
		log.info("3) Ms.");
		log.info("4) Dr.");
		log.info("5) I wish not to answer.");
		
		int ch = AcquireInput.intInput(sc);
		
		switch (ch) {
		case 1:
			salutation = "Mr.";
			break;
		case 2:
			salutation = "Mrs.";
			break;
		case 3:
			salutation = "Ms.";
			break;
		case 4:
			salutation = "Dr.";
			break;
		case 5:
			log.info("You chose not to answer.");
			break;
	
		default:
			log.info("You chose to skip.");
			break;
			}
			
		if(salutation!=null) {
			log.info("You chose "+salutation+" as your salutation!");
		}
		
		return salutation;
	}

	public static Date dob(Scanner sc, boolean isEmployee) {
		String dobEntered = null;
		Date dob = null;
		boolean isInvalidDate = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" date of birth using the following format.");
			log.info("yyyy-mm-dd For example: 1950-01-25");
			
			dobEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidDob(dobEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("Date of birth entered cannot be unaccepted.");
				continue;
			}
			
			LocalDate dobEnteredLd;
			try {
				dobEnteredLd = LocalDate.parse(dobEntered, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			} catch (Exception e) {
				log.info("It's not a valid date. Please try again.");
				continue;
			}
			
			if(!(dobEnteredLd.compareTo(LocalDate.now().minusYears(16))<=0)) {
				log.info("I'm sorry. A MyCardealer customer has to be 16 years old or older to register an account.");
				log.info("Date of birth entered is unaccepted.");
				continue;
			}
			
			log.info("Date of birth entered is accepted!");
			java.sql.Date dobsql = Date.valueOf(dobEnteredLd);
			dob = dobsql;
			
			isInvalidDate = false;
			
		} while (isInvalidDate);
		
		return dob;
	}

	public static String address(Scanner sc, boolean isEmployee) {
		String AddressEntered = null;
		boolean isInvalidAddress = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" address using the following format.");
			log.info("1111 East Street Name street, City, State, 5 didgit zip");
			
			AddressEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidAddress(AddressEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("Address entered cannot be unaccepted.");
				continue;
			}
			
			log.info("Address entered is accepted!");
			
			isInvalidAddress = false;
			
		} while (isInvalidAddress);
		
		return AddressEntered;
	}

	public static long phoneNumber(Scanner sc, boolean isEmployee, int phone) {
		String phoneEntered = null;
		boolean isInvalidPhone = true;
		String phoneType = "primary";
		long phoneOutput = 0l;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			if (phone==2) {
				phoneType = "second";
			}
			
			log.info("Please enter "+answeringTarget(isEmployee)+" "+phoneType+" contact number using the following format.");
			log.info("111-111-1111");
			
			if (phone==2) {
				log.info("If you don't wish to enter a second contact number, press enter to skip.");
			}
			
			phoneEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidPhone(phoneEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				if (phone ==2 ) {
					log.info("You chose to skip entering a second contact number.");
				}else {
					log.info("Contact number entered cannot be unaccepted.");
				}
				continue;
			}
			
			log.info("Contact number  entered is accepted!");
			phoneEntered = phoneEntered.replaceAll("\\D", "");
			phoneOutput = Long.parseLong(phoneEntered);
			
			isInvalidPhone = false;
			
		} while (isInvalidPhone && phone != 2);
		
		return phoneOutput;
	}

	public static String email(Scanner sc, boolean isEmployee) {
		String emailEntered = null;
		boolean isInvalidEmail = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" email.");
			
			emailEntered = AcquireInput.stringInput(sc);
			
			if(!Validation.isValidEmail(emailEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("Email entered cannot be unaccepted.");
				continue;
			}
			
			//chick if email is already registered
			customerCrudService = new CustomerCrudServiceImpl();
			try {
				if (customerCrudService.isEmailTaken(emailEntered)) {
					log.info("Email is already registered. Please try another email.");
					continue;
				}
				
			} catch (BusinessException e) {
				log.info(e.getMessage());
			}
			
			
			log.info("Email entered is accepted!");
			
			isInvalidEmail = false;
			
		} while (isInvalidEmail);
		
		return emailEntered;
	}

	public static int creditScore(Scanner sc, boolean isEmployee) {
		int creditScoreEntered = 0;
		boolean isCreditScore = true;
		
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("Please enter "+answeringTarget(isEmployee)+" credit score.");
			log.info("The range of credit score is 300 - 850.");
			log.info("Enter 299 if the customer don't know his/her credit score.");
			
			creditScoreEntered = AcquireInput.intInput(sc);
			
			if(!Validation.isValidCreditScore(creditScoreEntered)) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("Credit score entered cannot be unaccepted.");
				continue;
			}
			
			log.info("Credit score entered is accepted!");
			
			isCreditScore = false;
			
		} while (isCreditScore);
		
		return creditScoreEntered;
	}

	public static String answeringTarget(boolean isEmployee) {
		String target = "your";
		
		if (isEmployee) {
			target = "customer's";
		}
		return target;
	}

}
