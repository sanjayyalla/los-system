package com.jocata.los.dao;

import com.jocata.los.entity.LoanApplication;
import com.jocata.los.enums.Status;

import java.math.BigDecimal;

public interface LoanDetailsDao {

    LoanApplication saveLoanApplication(LoanApplication loanApplication);

    LoanApplication getLoanApplicationById(Integer applicationId);

    LoanApplication updateLoanApplication(Integer loanApplicationId, BigDecimal approvedAmount, Status loanStatus);
}
