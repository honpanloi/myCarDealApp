package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Loan;

public interface LoanCrudDAO {
	int creatNewLoan(Loan loan)throws BusinessException;
}
