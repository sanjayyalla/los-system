package com.jocata.los.dao;

import com.jocata.los.entity.LoanApplication;

public interface LoanDetailsDao {

    LoanApplication saveLoanApplication(LoanApplication loanApplication);

    LoanApplication getLoanApplicationById(Integer applicationId);
}
