package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.EmployeeLoginDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Employee;

public class EmployeeLoginDAOImpl implements EmployeeLoginDAO {

	@Override
	public Employee getEmployeeByLogin(String login_user_name, String login_password) throws BusinessException {
		Employee employee = null;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select * from my_car_dealer.employee where login_user_name = ? and login_password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,login_user_name);
			preparedStatement.setString(2,login_password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setFirst_name(resultSet.getString("first_name"));
				employee.setLast_name(resultSet.getString("last_name"));

				
			}else {
				throw new BusinessException("User not found. Login unsuccessful.");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return employee;
	}

}
