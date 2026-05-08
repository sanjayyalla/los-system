package com.lossystem.los.service;

public interface LoanProcessService {
    String approveLoan(String loanApplicationId, String requestedAmount);

    String rejectLoan(String loanApplicationId);
}
