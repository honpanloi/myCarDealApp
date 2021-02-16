package com.app.menu;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.menuFunction.AcquireInput;
import com.app.menuFunction.AcquireRegistrationInfo;
import com.app.menuFunction.PrintMessage;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.service.CustomerCrudService;
import com.app.service.EmployeeLoginService;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.service.impl.EmployeeLoginServiceImpl;

public class LoginMenu {
	
	private static Logger log = Logger.getLogger(LoginMenu.class);
	static CustomerCrudService customerCrudService;
	static EmployeeLoginService employeeLoginService;
	
	
	
	public static void CustomerLogin(Scanner sc, Customer customer) {

		int ch = 0;
		do {
			//Space out the old messages
			PrintMessage.spaceOutTheOldMessages(log);
			
			
			//options
			log.info("Welcome!");
			log.info("Please login or register to move forward.");
			log.info("1) Login");
			log.info("2) Register");
			log.info("3) Go back");
			
			ch = AcquireInput.intInput(sc);
			
			switch (ch) {
			
			case 1:		customerLogin(sc);
				break;
			case 2:		createCustomerByCustomer(sc);
				break;
			case 3:
				log.info("Going back...");
				break;
	
			default: PrintMessage.invalidInputMessage(log); break;
			}
			
		} while (ch!=3);
		
	}

	public static void EmployeeLogin(Scanner sc, Employee employee) {
		employee = new Employee();
		//Space out the old messages
		PrintMessage.spaceOutTheOldMessages(log);
		
		//login employee
		log.info("Please enter your user name.");
		String user_nameEntered = sc.nextLine();
		
		if(user_nameEntered == null) {
			log.info("You did not enter a user name. Please try again.");
			return;
		}
		
		log.info("Please enter your password");
		String passwordEntered = sc.nextLine();
		
		if(passwordEntered == null) {
			log.info("You did not enter a password. Please try again.");
			return;
		}
		
		
		employeeLoginService = new EmployeeLoginServiceImpl();
		try {
			employee = employeeLoginService.getEmployeeByLogin(user_nameEntered, passwordEntered);
		} catch (BusinessException e) {
			employee = null;
			log.info("Login unsuccessful. Please try again.");
		}
		
		//exit method when login unsuccessful
		if(employee == null) {
			return;
		}
		
		//direct customer to main menu if successful
		HomeMenu.employee(employee, sc);
	}

	private static void customerLogin(Scanner sc) {
		Customer customer;
		//Space out the old messages
		PrintMessage.spaceOutTheOldMessages(log);
		
		//login Customer
		log.info("Please enter your user name.");
		String user_nameEntered = sc.nextLine();
		
		if(user_nameEntered == null) {
			log.info("You did not enter a user name. Please try again.");
			return;
		}
		
		log.info("Please enter your password");
		String passwordEntered = sc.nextLine();
		
		if(passwordEntered == null) {
			log.info("You did not enter a password. Please try again.");
			return;
		}
		
		
		customerCrudService = new CustomerCrudServiceImpl();
		try {
			customer = customerCrudService.getCustomerByLogin(user_nameEntered, passwordEntered);
		} catch (BusinessException e) {
			customer = null;
			e.printStackTrace();
		}
		
		//exit method when login unsuccessful
		if(customer == null) {
			log.info("Login unsuccessful. Please try again.");
			return;
		}
		
		//direct customer to main menu if successful
		HomeMenu.customer(customer,sc);
	}

	private static void createCustomerByCustomer(Scanner sc) {
		Customer customer;
		CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();
		
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("Thank you for your interest of becoming a MyCarDealer customer.\n");

		int ch = 0;
		do {
			log.info("In order to complete the regisration, you will be required to enter the following infomation.");
			log.info("First Name, Last Name, Date of Birth, Social Security Number, ");
			log.info("Current Address, Phone Number, Email, Credit Score, Driver's Licence, etc\n");
			log.info("Do you wish to continue?");
			log.info("1) Yes");
			log.info("2) No");
			
			ch = AcquireInput.intInput(sc);
			switch (ch) {
			case 1:
				
				customer = new Customer();
				customer.setLogin_user_name(AcquireRegistrationInfo.userName(sc));
				customer.setLogin_password(AcquireRegistrationInfo.password(sc));
				customer.setSsn(AcquireRegistrationInfo.ssn(sc, false));
				customer.setDl(AcquireRegistrationInfo.driversLicence(sc, false));
				customer.setFirst_name(AcquireRegistrationInfo.firstOrLastName(sc, false, true));
				customer.setLast_name(AcquireRegistrationInfo.firstOrLastName(sc, false, false));
				customer.setSalutation(AcquireRegistrationInfo.salutation(sc, false));
				customer.setDob(AcquireRegistrationInfo.dob(sc, false));
				customer.setAddress(AcquireRegistrationInfo.address(sc, false));
				customer.setPhone1(AcquireRegistrationInfo.phoneNumber(sc, false, 1));
				customer.setPhone1(AcquireRegistrationInfo.phoneNumber(sc, false, 2));
				customer.setEmail(AcquireRegistrationInfo.email(sc, false));
				customer.setCredit_score(AcquireRegistrationInfo.creditScore(sc, false));
				
				try {
					int c =	customerCRUDService.createNewCustomerByCustomer(customer);
					if (c>0) {
						PrintMessage.spaceOutTheOldMessages(log);
						log.info("Registration completed.");
						
					}else {
						log.info("Registration was not completed.");
					}
				} catch (BusinessException e) {
					e.printStackTrace();
					log.info(e.getMessage());
				}
				
				ch = 2;

				break;
				
			case 2:
				log.info("Going back");
				break;


			default: PrintMessage.optionNotAvailable(log);	break;
			}
		} while (ch!=2);

	}
}
