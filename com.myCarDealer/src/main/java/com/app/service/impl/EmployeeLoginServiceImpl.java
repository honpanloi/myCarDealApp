package com.app.service.impl;

import com.app.dao.EmployeeLoginDAO;
import com.app.dao.impl.EmployeeLoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.service.EmployeeLoginService;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {
	
	EmployeeLoginDAO employeeLoginDAO = new EmployeeLoginDAOImpl();

	@Override
	public Employee getEmployeeByLogin(String login_user_name, String login_password) throws BusinessException {
		Employee employee = null;
		if(login_user_name!=null && login_password!=null) {
			employee = employeeLoginDAO.getEmployeeByLogin(login_user_name, login_password);
		}else {
			throw new BusinessException("User name or password is empty. Login unsuccessful. Please try again.");
		}
		
		return employee;
	}

}
