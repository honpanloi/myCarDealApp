package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerCrudService {
	
	Customer getCustomerByLogin(String login_user_name, String login_password)throws BusinessException;
	int createNewCustomerByCustomer(Customer customer) throws BusinessException;
	int createNewCustomerByEmployee(Customer customer, long employee_id) throws BusinessException;
	boolean isLoginUserNameTaken(String userName) throws BusinessException;
	boolean isSsnTaken(long ssn) throws BusinessException;
	boolean isDriversLicenceTaken(String dl) throws BusinessException;
	boolean isEmailTaken(String email) throws BusinessException;
	long getCustomerIdByEmail(String email) throws BusinessException;
}
