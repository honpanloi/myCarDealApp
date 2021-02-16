package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.model.Payment;

public interface PaymentCrudService {
	List<Payment> viewAllRemainPaymentsForACar(long car_id)throws BusinessException;
	List<Payment> viewMostRecent100Payments(long customer_id)throws BusinessException;
	int createDownPayment(Payment payment)throws BusinessException;
	int createPaymentsWithALoan(Loan loan)throws BusinessException;
}
