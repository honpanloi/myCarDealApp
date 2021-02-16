package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Car;

public interface CarCrudService {

	int addCarForSale(Car car) throws BusinessException;
	List<Car> viewAllCarsInLot() throws BusinessException;
	int confirmCustomerPickup(long car_id) throws BusinessException;
	List<Car> viewAllCarsOwnedByCustomer(long customer_id) throws BusinessException;
}
