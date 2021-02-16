package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.model.Payment;
import com.app.service.impl.PaymentCrudServiceImpl;
import com.app.util.Tool;

class PaymentCrudServiceTest {

	static PaymentCrudService paymentCrudService;
	
	@BeforeAll
	static void setup() {
		paymentCrudService = new PaymentCrudServiceImpl();
	}
	
	@Test
	void testViewAllRemainPaymentsOfCustomer() {
		System.out.println("testViewAllRemainPaymentsOfCustomer");
		List<Payment> result = new ArrayList<Payment>();
		try {
			result = paymentCrudService.viewAllRemainPaymentsForACar(1l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(result);
		
		for (Payment p : result) {
			System.out.println(p.toString());
		}
		
	}
	
	@Test
	void testViewMostRecent100Payments() {
		System.out.println("testViewMostRecent100Payments");
		List<Payment> result = new ArrayList<Payment>();
		try {
			result = paymentCrudService.viewMostRecent100Payments(1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(result);
		
		for (Payment p : result) {
			System.out.println(p.toString());
		}
		
	}
	
	@Test
	void testCreateDownPayment() {
		
		Payment payment = new Payment();
		payment.setCar_id(26);
		payment.setCustomer_id(1);
		payment.setDate_completed(Date.valueOf(LocalDate.now()));
		payment.setDate_generated(Tool.getPrintedCurrentDate());
		payment.setInterest(0d);
		payment.setIs_late(false);
		payment.setPrincipal(999);
		payment.setStatus("completed");
		payment.setTotal_amount(999);
		
		try {
			int c = paymentCrudService.createDownPayment(payment);
			
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void createPaymentsWithALoan() {
		
		Loan loan = new Loan();
		loan.setAmount_started(30000d);
		loan.setAnn_interest_rate(0);
		loan.setCar_id(25l);
		loan.setCustomer_id(1l);
		loan.setDate_started(Date.valueOf(LocalDate.now()));
		loan.setHas_late_payment(false);
		loan.setLoan_provider("Test provider");
		loan.setMonthly_payment(234.5d);
		loan.setTotal_payment_num(36);
		
		try {
			int c = paymentCrudService.createPaymentsWithALoan(loan);
			
			assertEquals(36, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
