package com.app.menu;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.menuFunction.AcquireCarInfo;
import com.app.menuFunction.AcquireInput;
import com.app.menuFunction.AcquireLoanInfo;
import com.app.menuFunction.AcquireRegistrationInfo;
import com.app.menuFunction.PrintMessage;
import com.app.model.Car;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.model.Loan;
import com.app.model.Offer;
import com.app.model.Payment;
import com.app.service.CarCrudService;
import com.app.service.CustomerCrudService;
import com.app.service.LoanCrudService;
import com.app.service.OfferCrudService;
import com.app.service.PaymentCrudService;
import com.app.service.impl.CarCrudServiceImpl;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.service.impl.LoanCrudServiceImpl;
import com.app.service.impl.OfferCrudServiceImpl;
import com.app.service.impl.PaymentCrudServiceImpl;

public class HomeMenu {
	
	private static Logger log = Logger.getLogger(HomeMenu.class);
	private static CarCrudService carCrudService;
	private static OfferCrudService offerCrudService;
	private static PaymentCrudService paymentCrudService;
	private static CustomerCrudService customerCrudService;
	private static LoanCrudService loanCrudService;
	
	
	public static void customer(Customer customer, Scanner sc) {
	
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("Hello! "+customer.getFirst_name()+". How can I help you today");
		
		int ch = 0;
		do {
			//print the options
			log.info("-----Main Menu----");
			log.info("Please choose an option below by entering the number associated with that option\n");
			log.info("1) View cars in the lot.");
			log.info("2) Make an offer for a car.");
			log.info("3) View the cars I own.");
			log.info("4) View remaining payments for a car.");
			log.info("5) Log out.");
			
			ch = AcquireInput.intInput(sc);
		
			switch (ch) {
		
			case 1:		viewAllCarsInLot();
				break;
			case 2:		makeAnOfferForACar(customer, sc);
				break;
			case 3:		viewAllCarsOwnedByCustomer(customer);
				break;
			case 4:		viewRemainingPaymentsForACar(customer, sc);
				break;
			case 5:
				log.info("Thank your for using our service!");
				log.info("logging out...");
				customer = null;
				break;
	
			default: PrintMessage.optionNotAvailable(log); 
				break;
		}
		
		} while (ch!=5);
	}

	public static void employee(Employee employee, Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		log.info("Hello! "+employee.getFirst_name()+".");
		
		int ch = 0;
		do {
			PrintMessage.spaceOutTheOldMessages(log);
			//print the options
			log.info("-----Main Menu----");
			log.info("Please choose an option below by entering the number associated with that option.\n");
			log.info("1) Add a car to the lot.");
			log.info("2) Accept an offer for a car.");
			log.info("3) Reject and offer for a car.");
			log.info("4) Register a customer.");
			log.info("5) Remove a car from the lot.");
			log.info("6) View the payments for a customer.");
			log.info("7) Log out.");
			
			ch = AcquireInput.intInput(sc);
		
			switch (ch) {
		
			case 1:	
				addACar(sc, employee.getId());
				break;
			case 2:	
				acceptOrRejectAnOffer(sc, employee.getId(),true);
				break;
			case 3:	
				acceptOrRejectAnOffer(sc, employee.getId(),false);
				break;
			case 4:		
				createCustomerByEmployee(sc, employee.getId());
				break;
			case 5:		
				
				log.info("Enter the car's id to deliever the car.");
				
				long car_id = AcquireInput.longInput(sc);
				
				carCrudService = new CarCrudServiceImpl();
				
				try {
					carCrudService.confirmCustomerPickup(car_id);
					log.info("The car is delieverd!");
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 6:		
				viewPaymentsForACustomer(sc);
				break;
			case 7:
				log.info("Thank your for using our service!");
				log.info("logging out...");
				employee = null;
				break;
	
			default: PrintMessage.optionNotAvailable(log); 
				break;
		}
		
		} while (ch!=7);
	}

	private static void acceptOrRejectAnOffer(Scanner sc, long employee_id, boolean isAccept) {
		
		log.info("Please enter the ID of the car.");
		long car_id = 0;
		car_id = AcquireInput.longInput(sc);
		
		offerCrudService = new OfferCrudServiceImpl();
		List<Offer> offers = null;
		try {
			offers = offerCrudService.getOffersByCarId(car_id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (offers.size()==0) {
			log.info("Unable to find offers belonged to the car.");
			return;
		}
		
		String action;
		if (isAccept) {
			action = "accept";
		}else {
			action = "reject";
		}
		
		log.info("Which offer do you want to "+action+"?");
		int index = 1;
		for (Offer offer : offers) {
			log.info(index+") "+offer.toString());
			index++;
		}
		
		int ch = AcquireInput.intInput(sc);
		
		if(ch<=0 || ch>offers.size()) {
			log.info("Input was invalid. No offer was "+action+"ed.");
			return;
		}
		
		Offer targetOffer = offers.get(ch - 1);
		
		if (!isAccept) {
			try {
				offerCrudService.rejectOfferbyid(targetOffer.getId(), employee_id);
				log.info("Offer rejected.");
				return;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return;
		}
		
		do {
			log.info("Does the customer need a loan?");
			log.info("1) Yes.");
			log.info("2) No.");
			ch = AcquireInput.intInput(sc);
		} while (ch!=1 && ch!=2);
		
		
		if(ch == 2) {
			try {
				offerCrudService.acceptOfferByEmployee(employee_id, targetOffer);
				log.info("Congratulations! The offer is completed. Please take care of the paper work.");
				return;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		
		
		Loan loan = new Loan();
		loan.setCar_id(targetOffer.getCar_id());
		loan.setAmount_started(AcquireLoanInfo.loanAmount_started(targetOffer.getOffer_amount(), sc, targetOffer));		
		loan.setCustomer_id(targetOffer.getOffer_by_customer());
		loan.setHas_late_payment(false);
		loan.setTotal_principal_remain(loan.getAmount_started());
		loan.setTotal_amount_paid(0d);
		loan.setTotal_interest_paid(0d);
		loan.setTotal_payment_completed(0);
		AcquireLoanInfo.selectLoanPlan(loan, sc);
		
		if(loan.getAmount_started()==-1) {
			log.info("I'm sorry. The deal is cancel.");
			return;
		}
		
		loanCrudService = new LoanCrudServiceImpl();
		
		try {
			loanCrudService.creatNewLoan(loan);
			log.info("The loan is accept!");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try {
			offerCrudService.acceptOfferByEmployee(employee_id, targetOffer);
			log.info("Congratulations! The offer is completed. Please take care of the paper work.");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		paymentCrudService = new PaymentCrudServiceImpl();
		try {
			int c = paymentCrudService.createPaymentsWithALoan(loan);
			log.info(c+" payments were inserted to the database.");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void viewPaymentsForACustomer(Scanner sc) {
		customerCrudService = new CustomerCrudServiceImpl();
		log.info("Please enter customer's email");
		String email = null;
		email = AcquireInput.stringInput(sc);
		long customer_id = 0l;
		try {
			customer_id = customerCrudService.getCustomerIdByEmail(email);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(customer_id==0) {
			log.info("Unable to find a customer by that email. Please try again.");
			return;
		}
			
		paymentCrudService = new PaymentCrudServiceImpl();
		List<Payment> payments = null;
		try {
			payments = paymentCrudService.viewMostRecent100Payments(customer_id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (payments.size()==0) {
			log.info("Unable to find payments for that customer.");
			return;
		}
		
		for (Payment payment : payments) {
			log.info(payment.toString());
		}
	}
	
	private static void viewRemainingPaymentsForACar(Customer customer, Scanner sc) {
		carCrudService = new CarCrudServiceImpl();
		try {
			List<Car> result = carCrudService.viewAllCarsOwnedByCustomer(customer.getId());
			
			if(result.size()==0) {
				log.info("Sorry. You don't have any cars in our record.");
				return;
			}
			
			log.info("Which car do you want to view the payments for?");
			int index = 1;
			for (Car c: result) {
				log.info(index+") "+c.getBasicInfo());
				index++;
			}
			
			int ch = AcquireInput.intInput(sc);
			
			long targetCar_id = result.get(ch-1).getId();
			
			paymentCrudService = new PaymentCrudServiceImpl();
			
			List<Payment> RemainPayments = paymentCrudService.viewAllRemainPaymentsForACar(targetCar_id);
			
			if(RemainPayments.size()==0) {
				PrintMessage.spaceOutTheOldMessages(log);
				log.info("There is not any payments remained for that car.");
				return;
			}
			
			PrintMessage.spaceOutTheOldMessages(log);
			for (Payment payment : RemainPayments) {
				log.info(payment.toString());
			}
			
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void makeAnOfferForACar(Customer customer, Scanner sc) {
		PrintMessage.spaceOutTheOldMessages(log);
		//Make an offer for a car.
		log.info("Please enter the ID of the car that you are interested in.");
		long car_id = AcquireInput.longInput(sc);
		
		log.info("Please enter your offer to that car.");
		double offer = AcquireInput.doubleInput(sc);
		
		try {
			offerCrudService = new OfferCrudServiceImpl();
			offerCrudService.createOfferByCustomer(customer.getId(), car_id, offer);
			PrintMessage.spaceOutTheOldMessages(log);
			log.info("An offer had been make to the car with the ID of "+car_id+" with $"+offer);
			log.info("Our employees will review your offer and contact the owner of the car.");
			log.info("Meanwhile, please feel free to make another offer.");
			log.info("Thank you for using our service.");
			
			PrintMessage.spaceOutTheOldMessages(log);
			
		} catch (BusinessException e) {
			log.info(e.getMessage());
		}
	}

	private static void viewAllCarsOwnedByCustomer(Customer customer) {
		carCrudService = new CarCrudServiceImpl();
		
		try {
			List<Car> result = carCrudService.viewAllCarsOwnedByCustomer(customer.getId());
			
			PrintMessage.spaceOutTheOldMessages(log);
			if(result.size()==0) {
				log.info("Sorry. You don't have any cars in our record.");
				return;
			}
			
			for (Car car : result) {
				log.info(car.getBasicInfo());
			}
			
			PrintMessage.spaceOutTheOldMessages(log);
		} catch (BusinessException e) {
			log.info(e.getMessage());
		}
	}

	private static void viewAllCarsInLot() {
		PrintMessage.spaceOutTheOldMessages(log);
		
		//View cars in the lot.
		carCrudService = new CarCrudServiceImpl();
		try {
			List<Car> result = carCrudService.viewAllCarsInLot();
			for (Car c : result) {
				log.info(c.getBasicInfo());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createCustomerByEmployee(Scanner sc, long employee_id) {
		Customer customer;
		CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();
		
		PrintMessage.spaceOutTheOldMessages(log);
	
		int ch = 0;
		do {
			log.info("In order to complete the regisration, the customer will be required to enter the following infomation.");
			log.info("First Name, Last Name, Date of Birth, Social Security Number, ");
			log.info("Current Address, Phone Number, Email, Credit Score, Driver's Licence, etc\n");
			log.info("Does the customer wish to continue?");
			log.info("1) Yes");
			log.info("2) No");
			
			ch = AcquireInput.intInput(sc);
			switch (ch) {
			case 1:
				
				customer = new Customer();
				customer.setSsn(AcquireRegistrationInfo.ssn(sc, true));
				customer.setDl(AcquireRegistrationInfo.driversLicence(sc, true));
				customer.setFirst_name(AcquireRegistrationInfo.firstOrLastName(sc, true, true));
				customer.setLast_name(AcquireRegistrationInfo.firstOrLastName(sc, true, false));
				customer.setSalutation(AcquireRegistrationInfo.salutation(sc, true));
				customer.setDob(AcquireRegistrationInfo.dob(sc, true));
				customer.setAddress(AcquireRegistrationInfo.address(sc, true));
				customer.setPhone1(AcquireRegistrationInfo.phoneNumber(sc, true, 1));
				customer.setPhone1(AcquireRegistrationInfo.phoneNumber(sc, true, 2));
				customer.setEmail(AcquireRegistrationInfo.email(sc, true));
				customer.setCredit_score(AcquireRegistrationInfo.creditScore(sc, true));
				
				try {
					int c =	customerCRUDService.createNewCustomerByEmployee(customer, employee_id);
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

	private static void addACar(Scanner sc, long employee_id) {
		Car car = new Car();
		car.setAdd_by_employee(employee_id);
		car.setOwner_id(AcquireCarInfo.getCustomerIdByEmail(sc));
		car.setManufacturer(AcquireCarInfo.manufacturer(sc));
		car.setModel(AcquireCarInfo.model(sc));
		car.setYear_made(AcquireCarInfo.yearMade(sc));
		car.setMileage(AcquireCarInfo.mileage(sc));
		car.setFuel_type(AcquireCarInfo.fuelType(sc));
		car.setEx_color(AcquireCarInfo.ex_color(sc));
		car.setIn_color(AcquireCarInfo.in_color(sc));
		car.setPark_location(AcquireCarInfo.park_location(sc));
		car.setOffer_desired(AcquireCarInfo.offer_desired(sc));
		
		carCrudService = new CarCrudServiceImpl();
		try {
			carCrudService.addCarForSale(car);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
