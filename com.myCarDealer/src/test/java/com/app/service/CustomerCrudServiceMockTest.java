package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.impl.CustomerCrudServiceImpl;

class CustomerCrudServiceMockTest {

	@InjectMocks
	static CustomerCrudService customerCrudService;
	@Mock
	static CustomerCrudDAOImpl customerCrudDAOImpl;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeAll
	static void beforeSetup() {
		customerCrudService = new CustomerCrudServiceImpl();
	}
	
	@Test
	void testCarCrudService() {
		Customer customer = new Customer();
		
		try {
			Mockito.when(customerCrudDAOImpl.createNewCustomer(customer, 0)).thenReturn(70);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			customerCrudService.createNewCustomerByCustomer(customer);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			verify(customerCrudDAOImpl).createNewCustomer(customer, 0);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(70, customerCrudDAOImpl.createNewCustomer(customer, 0));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
