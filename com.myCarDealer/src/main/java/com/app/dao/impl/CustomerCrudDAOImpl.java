package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CustomerCrudDAOImpl implements CustomerCrudDAO {

	@Override
	public Customer getCustomerByLogin(String login_user_name, String login_password) throws BusinessException {
		Customer customer = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select * from my_car_dealer.customer where login_user_name = ? and login_password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,login_user_name);
			preparedStatement.setString(2,login_password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getLong("id"));
				customer.setFirst_name(resultSet.getString("first_name"));
				customer.setLast_name(resultSet.getString("last_name"));
				customer.setSsn(resultSet.getInt("ssn"));
				customer.setSalutation(resultSet.getString("salutation"));
				customer.setDob(resultSet.getDate("dob"));
				customer.setPhone1(resultSet.getLong("phone1"));
				customer.setPhone2(resultSet.getLong("phone2"));
				customer.setRegister_by_employee(resultSet.getLong("register_by_employee"));
				customer.setCredit_score(resultSet.getInt("credit_score"));
				customer.setAddress(resultSet.getString("address"));
				customer.setEmail(resultSet.getString("email"));
				
			}else {
				throw new BusinessException("User not found. Login unsuccessful.");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		
		return customer;
	}

	@Override
	public int createNewCustomer(Customer customer, long employee_id) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = null;
			if(employee_id==0) {
				sql = "insert into my_car_dealer.customer(first_name,last_name,ssn,login_user_name,login_password,salutation,dob,phone1,phone2,credit_score,address,email,dl) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}else {
				sql = "insert into my_car_dealer.customer(first_name,last_name,ssn,login_user_name,login_password,salutation,dob,phone1,phone2,credit_score,address,email,dl,register_by_employee) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,customer.getFirst_name());
			preparedStatement.setString(2,customer.getLast_name());
			preparedStatement.setInt(3,customer.getSsn());
			preparedStatement.setString(4,customer.getLogin_user_name());
			preparedStatement.setString(5,customer.getLogin_password());
			preparedStatement.setString(6,customer.getSalutation());
			preparedStatement.setDate(7,customer.getDob());
			preparedStatement.setLong(8,customer.getPhone1());
			preparedStatement.setLong(9,customer.getPhone2());
			preparedStatement.setInt(10,customer.getCredit_score());
			preparedStatement.setString(11,customer.getAddress());
			preparedStatement.setString(12,customer.getEmail());
			preparedStatement.setString(13,customer.getDl());
			if(employee_id!=0) {
				preparedStatement.setLong(14,employee_id);
			}
			
			
			c += preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
	}

	public int deleteCustomerByFirstNameAnsLastName(String first_name, String last_name) throws BusinessException{
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "delete from my_car_dealer.customer where first_name = ? and last_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			
			c =+ preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {

			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
		
	}

	@Override
	public boolean isLoginUserNameTaken(String userName) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select login_user_name from my_car_dealer.customer where login_user_name =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userName);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {

			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public boolean isSsnTaken(long ssn) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select ssn from my_car_dealer.customer where ssn = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,ssn);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {

			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public boolean isDriversLicenceTaken(String dl) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select dl from my_car_dealer.customer where dl = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,dl);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {

			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public boolean isEmailTaken(String email) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select email from my_car_dealer.customer where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {

			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public long getCustomerIdByEmail(String email) throws BusinessException {
		long id = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select id from my_car_dealer.customer where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getLong("id");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return id;
	}
}
