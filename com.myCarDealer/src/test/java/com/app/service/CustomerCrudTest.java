package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.impl.CustomerCrudServiceImpl;

class CustomerCrudTest {

	public static CustomerCrudService customerCrudService;
	public static CustomerCrudDAOImpl customerCrudDaoImpl;
	
	@BeforeAll
	public static void setup() {
		customerCrudService = new CustomerCrudServiceImpl();
		customerCrudDaoImpl = new CustomerCrudDAOImpl();
	}
	
	@Test
	void testGetCustomerByLogin() {
		String login_user_name = "111111";
		String login_password = "111111";
		
		Customer customer = null;
		try {
			customer = customerCrudService.getCustomerByLogin(login_user_name, login_password);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(customer);
		System.out.println(customer.toString());
		
	}
	
	@Test
	void testCreateNewCustomerByCustomer() {
		Customer customer = new Customer();
		customer.setAddress("fake address");
		customer.setCredit_score(755);
		customer.setDl("L0004443333");
		customer.setEmail("fake@gmail.com");
		customer.setFirst_name("Test04");
		customer.setLast_name("Test04");
		customer.setLogin_password("A123456a");
		customer.setLogin_user_name("436273437");
		customer.setPhone1(8885557777l);
		customer.setPhone2(2220001111l);
		customer.setSalutation("Mr.");
		customer.setSsn(333445578);
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
	
	@Test
	void testCreateNewCustomerByEmployee() {
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
			int c = customerCrudService.createNewCustomerByEmployee(customer, 5000);
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testIsLoginUserNameTaken() {
		String userName = "111111";
		boolean isTaken = false;
		try {
			isTaken = customerCrudService.isLoginUserNameTaken(userName);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(isTaken);
		
		userName = "adfhdtyer";
		try {
			isTaken = customerCrudService.isLoginUserNameTaken(userName);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(isTaken);
	}
	
	@Test
	void testIsSsnTaken() {
		long ssn = 111223333l;
		boolean isTaken = false;
		try {
			isTaken = customerCrudService.isSsnTaken(ssn);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(isTaken);
		
		ssn = 245763454l;
		try {
			isTaken = customerCrudService.isSsnTaken(ssn);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(isTaken);
	}
	
	@Test
	void testIsDlTaken() {
		String dl = "L000111777";
		boolean isTaken = false;
		try {
			isTaken = customerCrudService.isDriversLicenceTaken(dl);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(isTaken);
		
		dl = "A1239872344";
		try {
			isTaken = customerCrudService.isDriversLicenceTaken(dl);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(isTaken);
	}
	
	@Test
	void testIsEmailTaken() {
		String email = "123@gmail.com";
		boolean isTaken = false;
		try {
			isTaken = customerCrudService.isEmailTaken(email);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(isTaken);
		
		email = "1q24624@gmail.com";
		try {
			isTaken = customerCrudService.isEmailTaken(email);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(isTaken);
	}
	
	@Test
	void testGetCustomerIdByEmail() {
		String email = "123@gmail.com";
		long id = 0;
		try {
			id = customerCrudService.getCustomerIdByEmail(email);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, id);
		
		email = "1q24624@gmail.com";
		try {
			id = customerCrudService.getCustomerIdByEmail(email);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, id);
	}
	
	@AfterAll
	static void undoChangesOnTheDB() {
		//remove Test04 from database
		try {
			customerCrudDaoImpl.deleteCustomerByFirstNameAnsLastName("Test04", "Test04");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//remove Test04 from database
		try {
			customerCrudDaoImpl.deleteCustomerByFirstNameAnsLastName("Test05", "Test05");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
