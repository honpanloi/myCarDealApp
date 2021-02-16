package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.dao.impl.CarCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Car;
import com.app.service.impl.CarCrudServiceImpl;

class CarCrudServiceTest {
	
	static CarCrudService CarCrudService;
	static CarCrudDAOImpl carCrudDAOImpl;
	
	@BeforeAll
	static void setup() {
		CarCrudService = new CarCrudServiceImpl();
		carCrudDAOImpl = new CarCrudDAOImpl();
	}
	
//	@Test
//	void testAddCarForSale() {
//		Car car = new Car();
//		car.setVin("12345678987456");
//		car.setManufacturer("Tesla");
//		car.setModel("Model 3");
//		car.setYear_made(2018);
//		car.setMileage(15688);
//		car.setFuel_type("Electric");
//		car.setEx_color("White");
//		car.setIn_color("White");
//		car.setOwner_id(1l);
//		car.setPark_location("D023");
//		car.setOffer_desired(20000.0d);
//		
//		int c = 0;
//		try {
//			c = CarCrudService.addCarForSale(car);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		assertEquals(1, c);
//	}
	
	@Test
	void testViewAllCars() {
		try {
			List<Car> result = null;
			 result = CarCrudService.viewAllCarsInLot();
			assertNotNull(result);
			
			for(Car c : result) {
				System.out.println(c.getBasicInfo());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testConfirmCustomerPickup() {
		try {
			Car car = new Car();
			car.setId(25);
			int c = CarCrudService.confirmCustomerPickup(car.getId());
			assertEquals(2, c);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testViewAllCarsOwnedByCustomer() {
		List<Car> result = null;
		try {
			result = CarCrudService.viewAllCarsOwnedByCustomer(1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(result);
		assertEquals(4, result.size());
		
		for(Car c : result) {
			System.out.println(c.toString());
		}
	}
	
	@AfterAll
	static void cleanTestGeneratedCar() {
		String vin = "12345678987456";
		try {
			carCrudDAOImpl.deleteCarByVin(vin);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
