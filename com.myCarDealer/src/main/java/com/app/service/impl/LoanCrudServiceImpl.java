package com.app.service.impl;

import com.app.dao.LoanCrudDAO;
import com.app.dao.impl.LoanCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Loan;
import com.app.service.LoanCrudService;

public class LoanCrudServiceImpl implements LoanCrudService {

	LoanCrudDAO loanCrudDAO = new LoanCrudDAOImpl();
	@Override
	public int creatNewLoan(Loan loan)
			throws BusinessException {
		int c = 0;
		c += loanCrudDAO.creatNewLoan(loan);
		return c;
	}

}
