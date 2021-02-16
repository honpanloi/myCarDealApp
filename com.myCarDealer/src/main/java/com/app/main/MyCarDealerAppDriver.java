package com.app.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.menu.LoginMenu;
import com.app.menuFunction.AcquireInput;
import com.app.menuFunction.PrintMessage;
import com.app.model.Customer;
import com.app.model.Employee;

public class MyCarDealerAppDriver {
	
	private static Logger log = Logger.getLogger(MyCarDealerAppDriver.class);

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Customer customer = null;
		Employee employee = null;
		
		int ch = 0;
		do {
			log.info("MyCarDealerApp V1.00");
			log.info("\nYou can choose an option below by entering the number associated with that option");
			log.info("1) I am a customer");
			log.info("2) I am an employee");
			log.info("3) Exit application");
						
			ch = AcquireInput.intInput(sc);
			
			switch (ch) {
			
			case 1:
				LoginMenu.CustomerLogin(sc,customer);
				break;
			case 2:
				LoginMenu.EmployeeLogin(sc,employee);
				break;
			case 3:
				log.info("Thank you for using MyCarDealerApp.");
				sc.close();
				break;

			default: PrintMessage.optionNotAvailable(log); break;
			}
		} while (ch!=3);

	}

}
