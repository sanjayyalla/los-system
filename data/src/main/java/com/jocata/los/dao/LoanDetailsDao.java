package com.jocata.los.dao;

import com.jocata.los.entity.LoanApplication;

import java.math.BigDecimal;

public interface LoanDetailsDao {

    LoanApplication saveLoanApplication(LoanApplication loanApplication);

    LoanApplication getLoanApplicationById(Integer applicationId);

    LoanApplication updateLoanApplication(Integer loanApplicationId, BigDecimal approvedAmount);
}
