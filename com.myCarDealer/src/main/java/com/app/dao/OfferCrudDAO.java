package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Offer;

public interface OfferCrudDAO {
	int createOfferByCustomer(long customer_id, long car_id, double offerAmount) throws BusinessException;
	int acceptOfferByEmployee(long employee_id, Offer offer) throws BusinessException;
	List<Offer> getOffersByCarId(long car_id) throws BusinessException;
	int rejectOfferbyid(long offer_id, long employee_id) throws BusinessException;
}
