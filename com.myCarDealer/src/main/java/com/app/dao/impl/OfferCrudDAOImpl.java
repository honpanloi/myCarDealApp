package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.OfferCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Offer;
import com.app.util.Tool;

public class OfferCrudDAOImpl implements OfferCrudDAO {

	@Override
	public int createOfferByCustomer(long customer_id, long car_id, double offerAmount) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "insert into my_car_dealer.offer (offer_by_customer,offer_created_time,offer_amount,car_id,status) values(?,?,?,?,'bidding')";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,customer_id);
			preparedStatement.setString(2,Tool.getPrintedCurrentDate());
			preparedStatement.setDouble(3,offerAmount);
			preparedStatement.setLong(4, car_id);
			
			c += preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
			
		return c;
	}
	
	public int deleteOfferByAmount(double offer_amount) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "delete from my_car_dealer.offer where \"offer_amount\" = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1,offer_amount);
			
			c += preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

	@Override
	public int acceptOfferByEmployee(long employee_id, Offer offer) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			//set the target offer's status to accepted
			String sql = "update my_car_dealer.offer set \"status\" = 'accepted' where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,offer.getId());
			
			c += preparedStatement.executeUpdate();
			
			//set completed_time for that offer
			sql = "update my_car_dealer.offer set \"offer_completed_time\" = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,Tool.getPrintedCurrentDate());
			preparedStatement.setLong(2,offer.getId());
			
			c += preparedStatement.executeUpdate();
			
			//set accepted_by for that offer
			sql = "update my_car_dealer.offer set \"accepted_by\" = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,employee_id);
			preparedStatement.setLong(2,offer.getId());
			
			c += preparedStatement.executeUpdate();
			
			//set rejected_by for the rest of the offers that belong to that car
			c += rejectAllOtherBiddingOffers(employee_id, offer.getCar_id());
			
			//set car owner_id to the new owner
			CarCrudDAOImpl carCrudDAOImpl = new CarCrudDAOImpl();
			c += carCrudDAOImpl.updateOwnerIdToNewOwnerAfterAnOfferIsAccepted(offer);
			
			//set the car status to ready to be pickup
			
			
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
	}
	
	public int rejectAllOtherBiddingOffers(long employee_id, long car_id) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			//set status to rejected
			String sql = "update my_car_dealer.offer set \"status\" = 'rejected' where \"car_id\" = ? and \"status\" = 'bidding'";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,car_id);
			
			c += preparedStatement.executeUpdate();

			
			//set offer_completed_time to current time
			sql = "update my_car_dealer.offer set \"offer_completed_time\" = ? where \"car_id\" = ? and \"status\" = 'rejected'";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,Tool.getPrintedCurrentDate());
			preparedStatement.setLong(2,car_id);
			
			c += preparedStatement.executeUpdate();

			//set rejected_by
			sql = "update my_car_dealer.offer set \"rejected_by\" = ? where \"car_id\" = ? and \"status\" = 'rejected'";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,employee_id);
			preparedStatement.setLong(2,car_id);
			
			c += preparedStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

	@Override
	public List<Offer> getOffersByCarId(long car_id) throws BusinessException {
		List<Offer> offers = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select * from my_car_dealer.offer where car_id = ? and \"status\" = 'bidding' order by offer_amount";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,car_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			offers = new ArrayList<Offer>();
			
			while (resultSet.next()) {
				Offer offer = new Offer();
				offer.setAccept_by(resultSet.getLong("accepted_by"));
				offer.setCar_id(car_id);
				offer.setId(resultSet.getLong("id"));
				offer.setOffer_amount(resultSet.getDouble("offer_amount"));
				offer.setOffer_by_customer(resultSet.getLong("offer_by_customer"));
				offer.setOffer_completed_time(resultSet.getString("offer_completed_time"));
				offer.setOffer_created_time(resultSet.getString("offer_created_time"));
				offer.setRejected_by(resultSet.getLong("rejected_by"));
				offer.setAccept_by(resultSet.getLong("accepted_by"));
				offer.setStatus(resultSet.getString("status"));
				
				offers.add(offer);
				
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
		
		return offers;
	}

	@Override
	public int rejectOfferbyid(long offer_id, long employee_id) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			//set status to rejected
			String sql = "update my_car_dealer.offer set \"status\" = 'rejected', offer_completed_time = ?, rejected_by = ? where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,Tool.getPrintedCurrentDate());
			preparedStatement.setLong(2, employee_id);
			preparedStatement.setLong(3,offer_id);
			
			c += preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

}
