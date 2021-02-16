package com.app.menuFunction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.model.Offer;
import com.app.model.Payment;
import com.app.service.PaymentCrudService;
import com.app.service.impl.PaymentCrudServiceImpl;
import com.app.util.Tool;

public class AcquireLoanInfo {

	private static Logger log = Logger.getLogger(AcquireLoanInfo.class);
	public static PaymentCrudService paymentCrudService;
	
	public static double loanAmount_started(double max_amount, Scanner sc, Offer offer) {
		double amount = 0;
		boolean isValidAmount = false;
		
		do {
			log.info("How much does the customer need to borrow?");
			
			amount = AcquireInput.doubleInput(sc);
			
			if (amount<1000 || amount>max_amount) {
				log.info("A loan must be between $1000 and the total amount of the offer.");
				continue;
			}
			
			Payment payment = new Payment();
			payment.setCar_id(offer.getCar_id());
			payment.setCustomer_id(offer.getOffer_by_customer());
			payment.setDate_completed(Date.valueOf(LocalDate.now()));
			payment.setDate_generated(Tool.getPrintedCurrentDate());
			payment.setInterest(0d);
			payment.setIs_late(false);
			payment.setPrincipal(max_amount - amount);
			payment.setStatus("completed");
			payment.setTotal_amount(max_amount - amount);
			
			paymentCrudService = new PaymentCrudServiceImpl();
			
			try {
				paymentCrudService.createDownPayment(payment);
				log.info("A down payment of $"+ (max_amount - amount) +" is charged.");
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			isValidAmount = true;
		} while (!isValidAmount);
		
		return amount;
	}
	
	public static void selectLoanPlan(Loan loan, Scanner sc) {
		
		boolean isPlanselected = false;
		
		do {
			log.info("Which plan does the customer prefer?");
			log.info("1) CityLoan Zero % (36 months, 0% interst rate.).");
			
			
			int ch = AcquireInput.intInput(sc);
			
			switch (ch) {
			case 1:
				
				loan.setMonthly_payment(loan.getAmount_started()/36);
				
				log.info("The estimated monthly will be $"+loan.getMonthly_payment());
				log.info("Does the customer wish to continue?");
				log.info("1) Yes.");
				log.info("2) No.");
				int ch1 = AcquireInput.intInput(sc);
				
				do {
					switch (ch1) {
					case 1:
						log.info("Thank you for the confirmation!");
						break;
					case 2:
						loan.setAmount_started(-1d);
						return;
					default:
						PrintMessage.optionNotAvailable(log);
						break;
					}
				} while (ch1!=1 && ch1!=2);
				
				
				loan.setAnn_interest_rate(0);
				loan.setTotal_payment_num(36);
				loan.setProjected_closing_date(Date.valueOf(LocalDate.now().plusMonths(36)));
				loan.setNext_payment_date(Date.valueOf(LocalDate.now().plusMonths(1)));
				loan.setDate_started(Date.valueOf(LocalDate.now()));
				loan.setLoan_provider("CityBank");
				
				
				
				isPlanselected = true;
				break;

			default:
				PrintMessage.optionNotAvailable(log);
				break;
			}
		} while (!isPlanselected);
		
	}
	
}
