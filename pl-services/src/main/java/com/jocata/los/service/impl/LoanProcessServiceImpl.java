package com.jocata.los.service.impl;

import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.enums.Status;
import com.jocata.los.service.LoanProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanProcessServiceImpl implements LoanProcessService {

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Override
    public String approveLoan(String loanApplicationId, String requestedAmount) {
        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(Integer.parseInt(loanApplicationId));
        if (requestedAmount == null || requestedAmount.isEmpty()) {
            BigDecimal loanAmountInApplication = loanApplication.getLoanAmount();
            BigDecimal approvalAmount = loanApplication.getApprovalAmount();
            BigDecimal finalApprovalAmount = loanAmountInApplication.min(approvalAmount);
            loanApplication.setApprovalAmount(finalApprovalAmount);
            loanApplication.setStatus(Status.APPROVED);
            LoanApplication updatedLoanApplication = loanDetailsDao.saveLoanApplication(loanApplication);
            if (updatedLoanApplication != null) {
                return "Loan Approved";
            }
        }
        loanApplication.setApprovalAmount(new BigDecimal(requestedAmount));
        loanApplication.setStatus(Status.APPROVED);
        LoanApplication updatedLoanApplication = loanDetailsDao.saveLoanApplication(loanApplication);
        if (updatedLoanApplication != null) {
            return "Loan Approved";
        }
        return "Not updated";
    }

    @Override
    public String rejectLoan(String loanApplicationId) {
        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(Integer.valueOf(loanApplicationId));
        loanApplication.setStatus(Status.REJECTED);
        loanApplication.setApprovalAmount(new BigDecimal(0));
        loanApplication.setActive(Boolean.FALSE);
        LoanApplication updatedLoanApplication = loanDetailsDao.saveLoanApplication(loanApplication);
        if (updatedLoanApplication.getStatus().equals(Status.REJECTED)) {
            return "Loan Rejected";
        }
        return "Loan Rejection Failed";
    }
}
