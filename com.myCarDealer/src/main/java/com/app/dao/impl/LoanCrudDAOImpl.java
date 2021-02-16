package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.LoanCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Loan;

public class LoanCrudDAOImpl implements LoanCrudDAO {

	@Override
	public int creatNewLoan(Loan loan)
			throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "insert into my_car_dealer.loan("
					+ "amount_started,loan_provider,"
					+ "date_started,next_payment_date,"
					+ "total_principal_remain,monthly_payment,"
					+ "ann_interest_rate,projected_closing_date,"
					+ "total_payment_num,"
					+ "car_id,customer_id) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, loan.getAmount_started());
			preparedStatement.setString(2, loan.getLoan_provider());
			preparedStatement.setDate(3, loan.getDate_started());
			preparedStatement.setDate(4, loan.getNext_payment_date());
			preparedStatement.setDouble(5, loan.getTotal_principal_remain());
			preparedStatement.setDouble(6, loan.getMonthly_payment());
			preparedStatement.setDouble(7, loan.getAnn_interest_rate());
			preparedStatement.setDate(8, loan.getProjected_closing_date());
			preparedStatement.setInt(9, loan.getTotal_payment_num());
			preparedStatement.setLong(10, loan.getCar_id());
			preparedStatement.setLong(11, loan.getCustomer_id());
			
			c += preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
	}

}
