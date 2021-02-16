package com.app.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.PaymentCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.model.Payment;
import com.app.util.Tool;

public class PaymnetCrudDAOImpl implements PaymentCrudDAO {

	@Override
	public List<Payment> viewAllRemainPaymentsForACar(long car_id) throws BusinessException {
		List<Payment> result = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select * from my_car_dealer.payment where car_id = ? and \"status\" != 'completed' order by date_due";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,car_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			result = new ArrayList<Payment>();
			while(resultSet.next()) {
				Payment payment = new Payment();
				setPaymentInfoFromResultSet(resultSet, payment);
				
				result.add(payment);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return result;
	}

	@Override
	public List<Payment> viewMostRecent100Payments(long customer_id) throws BusinessException {
		List<Payment> result = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select * from my_car_dealer.payment where customer_id = ? order by date_due limit 100";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customer_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			result = new ArrayList<Payment>();
			while(resultSet.next()) {
				Payment payment = new Payment();
				setPaymentInfoFromResultSet(resultSet, payment);
				
				result.add(payment);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return result;
	}

	@Override
	public int createDownPayment(Payment payment) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "insert into my_car_dealer.payment (total_amount,date_generated,\"status\",interest,principal,date_completed,is_late,customer_id,car_id) values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, payment.getTotal_amount());
			preparedStatement.setString(2, payment.getDate_generated());
			preparedStatement.setString(3, payment.getStatus());
			preparedStatement.setDouble(4, payment.getInterest());
			preparedStatement.setDouble(5, payment.getPrincipal());
			preparedStatement.setDate(6, payment.getDate_completed());
			preparedStatement.setBoolean(7, payment.getIs_late());
			preparedStatement.setLong(8, payment.getCustomer_id());
			preparedStatement.setLong(9, payment.getCar_id());
			
			c += preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

	@Override
	public int createPaymentsWithALoan(Loan loan) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "insert into my_car_dealer.payment (total_amount,date_generated,\"status\",interest,principal,is_late,customer_id,car_id,date_due) values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			for (int i = 1; i <= loan.getTotal_payment_num(); i++) {
				preparedStatement.setDouble(1, loan.getMonthly_payment());
				preparedStatement.setString(2, Tool.getPrintedCurrentDate());
				preparedStatement.setString(3, "pending");
				preparedStatement.setDouble(4, 0d);
				preparedStatement.setDouble(5, loan.getMonthly_payment());
				preparedStatement.setBoolean(6, false);
				preparedStatement.setLong(7, loan.getCustomer_id());
				preparedStatement.setLong(8, loan.getCar_id());
				preparedStatement.setDate(9, Date.valueOf(LocalDate.now().plusMonths(i)));
				c += preparedStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

	private void setPaymentInfoFromResultSet(ResultSet resultSet, Payment payment) throws SQLException {
		payment.setCar_id(resultSet.getLong("car_id"));
		payment.setCustomer_id(resultSet.getLong("customer_id"));
		payment.setDate_completed(resultSet.getDate("date_completed"));
		payment.setDate_due(resultSet.getDate("date_due"));
		payment.setDate_generated(resultSet.getString("date_generated"));
		payment.setId(resultSet.getLong("id"));
		payment.setInterest(resultSet.getDouble("interest"));
		payment.setIs_late(resultSet.getBoolean("is_late"));
		payment.setLoan_id(resultSet.getLong("loan_id"));
		payment.setPrincipal(resultSet.getDouble("principal"));
		payment.setStatus(resultSet.getString("status"));
		payment.setTotal_amount(resultSet.getDouble("total_amount"));
	}
	

}
