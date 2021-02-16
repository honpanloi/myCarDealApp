package com.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.impl.CustomerCrudServiceImpl;

class CustomerCrudServiceTest2 {

	static CustomerCrudService customerCrudService;
	static CustomerCrudDAOImpl customerCrudDaoImpl;
	
	@BeforeAll
	static void setup() {
		customerCrudService = new CustomerCrudServiceImpl();
		customerCrudDaoImpl = new CustomerCrudDAOImpl();
	}
	
	@Test
	void testCreateNewCustomerByCustomer() {
		
		Customer customer = new Customer();
		customer.setAddress("fake address");
		customer.setCredit_score(755);
		customer.setDl("L0004443344");
		customer.setEmail("fake2@gmail.com");
		customer.setFirst_name("Test05");
		customer.setLast_name("Test05");
		customer.setLogin_password("777777");
		customer.setLogin_user_name("777777");
		customer.setPhone1(8885557777l);
		customer.setPhone2(2220001111l);
		customer.setSalutation("Mr.");
		customer.setSsn(333446678);
		Date dob = new Date(1999-01-01);
		customer.setDob(dob);
		
		try {
			int c = customerCrudService.createNewCustomerByCustomer(customer);
			
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		}
	
	
	@AfterAll
	static void cleaning() {
		try {
			customerCrudDaoImpl.deleteCustomerByFirstNameAnsLastName("Test05", "Test05");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
