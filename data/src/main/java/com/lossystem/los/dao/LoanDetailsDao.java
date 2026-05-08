package com.lossystem.los.dao;

import com.lossystem.los.entity.LoanApplication;
import com.lossystem.los.enums.Status;

import java.math.BigDecimal;

public interface LoanDetailsDao {

    LoanApplication saveLoanApplication(LoanApplication loanApplication);

    LoanApplication getLoanApplicationById(Integer applicationId);

    LoanApplication updateLoanApplication(Integer loanApplicationId, BigDecimal approvedAmount, Status loanStatus);
}
