package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.service.impl.LoanCrudServiceImpl;

class LoanCrudServiceTest {

	static LoanCrudService loanCrudService;
	
	@BeforeAll
	static void setup(){
		loanCrudService = new LoanCrudServiceImpl();
	}
	
	@Test
	void testCreatNewLoan() {
		Loan loan = new Loan();	
		loan.setAmount_started(19999d);
		loan.setLoan_provider("Chest");
		loan.setDate_started(Date.valueOf("2021-02-07"));
		loan.setNext_payment_date(Date.valueOf("2021-03-07"));
		loan.setTotal_principal_remain(19999d);
		loan.setMonthly_payment(555.55d);
		loan.setAnn_interest_rate(0);
		loan.setProjected_closing_date(Date.valueOf("2024-03-07"));
		loan.setTotal_payment_num(36);
		loan.setCar_id(26);
		loan.setCustomer_id(1);
		
		System.out.println(loan.toString());
		
		try {
			int c = loanCrudService.creatNewLoan(loan);
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
