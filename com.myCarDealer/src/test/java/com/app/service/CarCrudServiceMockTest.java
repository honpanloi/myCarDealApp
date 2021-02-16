package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.app.exception.BusinessException;
import com.app.model.Car;
import com.app.service.impl.CarCrudServiceImpl;

public class CarCrudServiceMockTest {

	
	static CarCrudServiceImpl carCrudServiceImpl = mock(CarCrudServiceImpl.class);

	static CarCrudService carCrudService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeAll
	public static void beforeSetup() {
		carCrudService = carCrudServiceImpl;
	}
	
	@Test
	void testCarCrudService() {
		Car car = new Car();
		try {
			Mockito.when(carCrudServiceImpl.addCarForSale(car)).thenReturn(3);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(3, carCrudService.addCarForSale(car));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			verify(carCrudServiceImpl).addCarForSale(car);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
