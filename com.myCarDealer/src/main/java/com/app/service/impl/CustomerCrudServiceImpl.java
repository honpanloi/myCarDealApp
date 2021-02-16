package com.app.service.impl;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.CustomerCrudService;

public class CustomerCrudServiceImpl implements CustomerCrudService {

	CustomerCrudDAO customerCrudDAO = new CustomerCrudDAOImpl();
	
	@Override
	public Customer getCustomerByLogin(String login_user_name, String login_password) throws BusinessException {
		Customer customer = null;
		if (login_user_name!=null && login_password!=null) {
			customer = customerCrudDAO.getCustomerByLogin(login_user_name, login_password);
		}else {
			throw new BusinessException("User name or password is empty. Login unsuccessful. Please try again.");
		}
		return customer;
	}

	@Override
	public int createNewCustomerByCustomer(Customer customer) throws BusinessException {
		int c = 0;
		long employee_id = 0;
		c = customerCrudDAO.createNewCustomer(customer, employee_id);
		return c;
	}

	@Override
	public int createNewCustomerByEmployee(Customer customer, long employee_id) throws BusinessException {
		int c = 0;
		c = customerCrudDAO.createNewCustomer(customer, employee_id);
		return c;
	}

	@Override
	public boolean isLoginUserNameTaken(String userName) throws BusinessException {
		boolean isTaken = true;
		isTaken = customerCrudDAO.isLoginUserNameTaken(userName);
		return isTaken;
	}

	@Override
	public boolean isSsnTaken(long ssn) throws BusinessException {
		boolean isTaken = true;
		isTaken = customerCrudDAO.isSsnTaken(ssn);
		return isTaken;
	}

	@Override
	public boolean isDriversLicenceTaken(String dl) throws BusinessException {
		boolean isTaken = true;
		isTaken = customerCrudDAO.isDriversLicenceTaken(dl);
		return isTaken;
	}

	@Override
	public boolean isEmailTaken(String email) throws BusinessException {
		boolean isTaken = true;
		isTaken = customerCrudDAO.isEmailTaken(email);
		return isTaken;
	}

	@Override
	public long getCustomerIdByEmail(String email) throws BusinessException {
		long id;
		id = customerCrudDAO.getCustomerIdByEmail(email);
		return id;
	}

}
