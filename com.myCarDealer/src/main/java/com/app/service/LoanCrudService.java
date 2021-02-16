package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Loan;

public interface LoanCrudService {
	int creatNewLoan(Loan loan)throws BusinessException;
}
