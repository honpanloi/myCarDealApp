package com.app.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.service.impl.EmployeeLoginServiceImpl;

class EmployeeLoginServiceTest {

	public static EmployeeLoginService employeeLoginServiceTest;
	
	@BeforeAll
	static void setup() {
		employeeLoginServiceTest = new EmployeeLoginServiceImpl();
	}
	
	@Test
	void test() {
		String login_user_name = "222222";
		String login_password = "222222";
		
		Employee employee = new Employee();
		try {
			employee = employeeLoginServiceTest.getEmployeeByLogin(login_user_name, login_password);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(employee);
		
		System.out.println(employee.toString());
	}

}
