package com.app.service.impl;

import java.util.List;

import com.app.dao.OfferCrudDAO;
import com.app.dao.impl.OfferCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Offer;
import com.app.service.OfferCrudService;

public class OfferCrudServiceImpl implements OfferCrudService {

	OfferCrudDAO offerCrudDAO = new OfferCrudDAOImpl();
	@Override
	public int createOfferByCustomer(long customer_id, long car_id, double offerAmount) throws BusinessException {
		int c = 0;
		
		if(car_id<=0) {
			throw new BusinessException("A car ID has to be greater than zero.");
		}
		
		if(offerAmount<=0.01d) {
			throw new BusinessException("An offer has to be greater than $0.01.");
		}
		
		c = offerCrudDAO.createOfferByCustomer(customer_id, car_id, offerAmount);
		
		return c;
	}
	@Override
	public int acceptOfferByEmployee(long employee_id, Offer offer) throws BusinessException {
		int c = 0;
		c = offerCrudDAO.acceptOfferByEmployee(employee_id, offer);
		return c;
	}
	@Override
	public List<Offer> getOffersByCarId(long car_id) throws BusinessException {
		List<Offer> offers = null;
		offers = offerCrudDAO.getOffersByCarId(car_id);
		return offers;
	}
	@Override
	public int rejectOfferbyid(long offer_id, long employee_id) throws BusinessException {
		int c = 0;
		c = offerCrudDAO.rejectOfferbyid(offer_id, employee_id);
		return c;
	}

}
