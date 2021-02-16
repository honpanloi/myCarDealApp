package com.app.service.impl;

import java.util.List;

import com.app.dao.CarCrudDAO;
import com.app.dao.impl.CarCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Car;
import com.app.service.CarCrudService;

public class CarCrudServiceImpl implements CarCrudService {

	CarCrudDAO carCrudDAO = new CarCrudDAOImpl();
	
	@Override
	public int addCarForSale(Car car) throws BusinessException {
		int c = 0;
		if(car!=null) {
			c = carCrudDAO.addCarForSale(car);
		}
		return c;
	}

	@Override
	public List<Car> viewAllCarsInLot() throws BusinessException {
		List<Car> result = null;
		result = carCrudDAO.viewAllCarsInLot();
		return result;
	}

	@Override
	public int confirmCustomerPickup(long car_id) throws BusinessException {
		int c = 0;
		c = carCrudDAO.confirmCustomerPickup(car_id);
		return c;
	}

	@Override
	public List<Car> viewAllCarsOwnedByCustomer(long customer_id) throws BusinessException {
		List<Car> result = null;
		result = carCrudDAO.viewAllCarsOwnedByCustomer(customer_id);
		return result;
	}

}
