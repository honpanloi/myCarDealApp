package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Car;

public interface CarCrudDAO {
	int addCarForSale(Car car) throws BusinessException;
	List<Car> viewAllCarsInLot() throws BusinessException;
	int confirmCustomerPickup(long car_id) throws BusinessException;
	List<Car> viewAllCarsOwnedByCustomer(long customer_id) throws BusinessException;
}
