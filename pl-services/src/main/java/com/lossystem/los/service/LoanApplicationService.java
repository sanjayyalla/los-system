package com.lossystem.los.service;

import com.lossystem.los.form.LoanApplicationForm;

public interface LoanApplicationService {
    LoanApplicationForm createLoanApplication(LoanApplicationForm form);

    LoanApplicationForm getLoanApplicationById(Integer loanApplicationId);
}
