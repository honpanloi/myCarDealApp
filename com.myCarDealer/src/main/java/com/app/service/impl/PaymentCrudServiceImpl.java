package com.app.service.impl;

import java.util.List;

import com.app.dao.PaymentCrudDAO;
import com.app.dao.impl.PaymnetCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.model.Payment;
import com.app.service.PaymentCrudService;

public class PaymentCrudServiceImpl implements PaymentCrudService {

	PaymentCrudDAO paymentCrudDAO = new PaymnetCrudDAOImpl();
	
	@Override
	public List<Payment> viewAllRemainPaymentsForACar(long car_id) throws BusinessException {
		List<Payment> result = null;
		result = paymentCrudDAO.viewAllRemainPaymentsForACar(car_id);
		return result;
	}

	@Override
	public List<Payment> viewMostRecent100Payments(long customer_id) throws BusinessException {
		List<Payment> result = null;
		result = paymentCrudDAO.viewMostRecent100Payments(customer_id);
		return result;
	}

	@Override
	public int createDownPayment(Payment payment) throws BusinessException {
		int c = 0;
		c = paymentCrudDAO.createDownPayment(payment);
		return c;
	}

	@Override
	public int createPaymentsWithALoan(Loan loan) throws BusinessException {
		int c = 0;
		
		c = paymentCrudDAO.createPaymentsWithALoan(loan);
		
		return c;
	}

}
