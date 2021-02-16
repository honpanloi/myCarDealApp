package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.CarCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Car;
import com.app.model.Offer;
import com.app.util.Tool;

public class CarCrudDAOImpl implements CarCrudDAO {

	@Override
	public int addCarForSale(Car car) throws BusinessException {
		int c=0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "insert into my_car_dealer.car(manufacturer,model,year_made,mileage,fuel_type,ex_color,in_color,owner_id,park_location,offer_desired,add_by_employee,date_added,status) values(?,?,?,?,?,?,?,?,?,?,?,?,'for_sale')";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,car.getManufacturer());
			preparedStatement.setString(2,car.getModel());
			preparedStatement.setInt(3,car.getYear_made());
			preparedStatement.setInt(4,car.getMileage());
			preparedStatement.setString(5,car.getFuel_type());
			preparedStatement.setString(6,car.getEx_color());
			preparedStatement.setString(7,car.getIn_color());
			preparedStatement.setLong(8,car.getOwner_id());
			preparedStatement.setString(9,car.getPark_location());
			preparedStatement.setDouble(10,car.getOffer_desired());
			preparedStatement.setLong(11, car.getAdd_by_employee());
			preparedStatement.setString(12, Tool.getPrintedCurrentDate());
				
			c += preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}
	
	public int deleteCarByVin(String vin) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "delete from my_car_dealer.car where vin = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,vin);
			
			c += preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return c;
	}

	@Override
	public List<Car> viewAllCarsInLot() throws BusinessException {
		List<Car> result = null;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select * from my_car_dealer.car where \"park_location\" != 'out'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			result = new ArrayList<Car>();
			while(resultSet.next()){
				Car car = new Car();
				getCarInfoFromResultSet(resultSet, car);
				
				result.add(car);
				
			}
		}catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return result;
	}

	public int updateOwnerIdToNewOwnerAfterAnOfferIsAccepted(Offer offer) throws BusinessException{
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			//update owner_id
			String sql = "update my_car_dealer.car set \"owner_id\" = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,offer.getOffer_by_customer());
			preparedStatement.setLong(2,offer.getCar_id());
			
			c += preparedStatement.executeUpdate();
			
			//update status to sold
			sql = "update my_car_dealer.car set \"status\" = 'sold' where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,offer.getCar_id());
			
			c += preparedStatement.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
	}

	@Override
	public int confirmCustomerPickup(long car_id) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			//update status for the car
			String sql = "update my_car_dealer.car set \"status\" = 'delivered' where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,car_id);
			
			c += preparedStatement.executeUpdate();
			
			sql = "update my_car_dealer.car set \"park_location\" = 'out' where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,car_id);
			
			c += preparedStatement.executeUpdate();
			
			
		}catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		return c;
	}

	@Override
	public List<Car> viewAllCarsOwnedByCustomer(long customer_id) throws BusinessException {
		List<Car> result = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select * from my_car_dealer.car where owner_id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,customer_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			result = new ArrayList<Car>();
			while(resultSet.next()) {
				Car car = new Car();
				
				getCarInfoFromResultSet(resultSet, car);
				
				result.add(car);
				
			}
		}catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact admin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact admin(sql)");
		}
		
		return result;
	}

	private void getCarInfoFromResultSet(ResultSet resultSet, Car car) throws SQLException {
		car.setCity_mpg(resultSet.getInt("city_mpg"));
		car.setDrive_train(resultSet.getString("drive_train"));
		car.setEx_color(resultSet.getString("ex_color"));
		car.setFuel_type(resultSet.getString("fuel_type"));
		car.setHighway_mpg(resultSet.getInt("highway_mpg"));
		car.setId(resultSet.getLong("id"));
		car.setIn_color(resultSet.getString("in_color"));
		car.setLoan_id(resultSet.getLong("loan_id"));
		car.setManufacturer(resultSet.getString("manufacturer"));
		car.setMileage(resultSet.getInt("mileage"));
		car.setModel(resultSet.getString("model"));
		car.setOffer_desired(resultSet.getDouble("offer_desired"));
		car.setOwner_id(resultSet.getLong("owner_id"));
		car.setPark_location(resultSet.getString("park_location"));
		car.setStatus(resultSet.getString("status"));
		car.setTransmission(resultSet.getString("transmission"));
		car.setVin(resultSet.getString("vin"));
		car.setYear_made(resultSet.getInt("year_made"));
	}
}
