package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginService {

	Employee getEmployeeByLogin(String login_user_name, String login_password) throws BusinessException;
}
