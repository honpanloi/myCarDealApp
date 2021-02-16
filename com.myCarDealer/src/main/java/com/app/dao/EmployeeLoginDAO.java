package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginDAO {

	Employee getEmployeeByLogin(String login_user_name, String login_password) throws BusinessException;
}
