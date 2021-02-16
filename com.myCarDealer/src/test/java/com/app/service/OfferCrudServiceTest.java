package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.dao.impl.OfferCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Offer;
import com.app.service.impl.OfferCrudServiceImpl;

class OfferCrudServiceTest {

	static OfferCrudService offerCrudService;
	static OfferCrudDAOImpl offerCrudDAOImpl;
	@BeforeAll
	static void setUp() {
		offerCrudService = new OfferCrudServiceImpl();
		offerCrudDAOImpl = new OfferCrudDAOImpl();
	}

	@Test
	void testCreateOfferByCustomer() {
		long customer_id = 1;
		long car_id = 25;
		double offerAmount = 6999.9898;
		
		try {
			int c = offerCrudService.createOfferByCustomer(customer_id, car_id, offerAmount);
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		offerAmount = -1d;
		
		try {
			int c = offerCrudService.createOfferByCustomer(customer_id, car_id, offerAmount);
			assertEquals(0, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
//  Small piece of acceptOfferByEmployee
//	@Test
//	void testRejectAllOtherOffers() {
//		try {
//			int c = offerCrudDAOImpl.rejectAllOtherBiddingOffers(5000, 25);
//			assertNotEquals(0, c);
//			System.out.println(c);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void testAcceptOfferByEmployee() {
		Offer offer = new Offer();
		offer.setId(10);
		offer.setCar_id(25);
		offer.setOffer_by_customer(3);
		try {
			int c = offerCrudService.acceptOfferByEmployee(5000, offer);
			assertTrue(c>=11);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	void testGetOffersByCarId() {
//		try {
//			List<Offer> offers = offerCrudService.getOffersByCarId(24);
//			
//			assertEquals(1, offers.size());
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void testRejectOfferbyid() {
		try {
			int c = offerCrudService.rejectOfferbyid(29, 5000);
			
			assertEquals(1, c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterAll
	static void cleanTestGeneratedOffer() {
		
		try {
			offerCrudDAOImpl.deleteOfferByAmount(6999.9898d);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
