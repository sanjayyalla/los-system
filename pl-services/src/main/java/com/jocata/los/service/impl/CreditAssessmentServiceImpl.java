package com.jocata.los.service.impl;

import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.CibilDetails;
import com.jocata.los.entity.Customer;
import com.jocata.los.entity.IncomeDetails;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.enums.Status;
import com.jocata.los.service.CibilService;
import com.jocata.los.service.CreditAssessmentService;
import com.jocata.los.utils.LoanCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditAssessmentServiceImpl implements CreditAssessmentService {

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Autowired
    private CibilService cibilService;

    @Override
    public String creditAssessment(String loanApplicationId) {

        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(Integer.parseInt(loanApplicationId));
        if (loanApplication == null) {
            return "Loan application not found";
        }
        Customer customer = loanApplication.getCustomer();
        if (customer.getCustomerId() != null) {
            String status = customer.getCibilDetails().getCreditStatus();
            if (status.equals("GOOD") || status.equals("AVERAGE")) {
//                String panNumber = customer.getPanNumber();
                IncomeDetails incomeDetails = loanApplication.getCustomer().getIncomeDetails();
                CibilDetails cibilDetails = loanApplication.getCustomer().getCibilDetails();
                int accountCount = cibilDetails.getNoOfActiveAccounts();
                Status loanStatus;
                if (accountCount > 5) {
                    loanApplication.setStatus(Status.REJECTED);
                    loanApplication.setActive(false);
                    loanDetailsDao.saveLoanApplication(loanApplication);
                    return "Existing active accounts are greater than 5, Loan cannot be approved";
                }
                int enquiriesCount = cibilDetails.getNoOfEnquiries();
                if (enquiriesCount > 5) {
                    loanApplication.setStatus(Status.REJECTED);
                    loanApplication.setActive(false);
                    loanDetailsDao.saveLoanApplication(loanApplication);
                    return "Enquiries are greater than 5 ,Loan cannot be approved";
                }
                BigDecimal effordableAmountPerMonth = incomeDetails.getMonthlyIncome().multiply(new BigDecimal("0.75")).subtract(cibilDetails.getEmisTotal());
                if (effordableAmountPerMonth.compareTo(BigDecimal.ZERO) <= 0) {
                    loanApplication.setStatus(Status.REJECTED);
                    loanApplication.setActive(false);
                    loanDetailsDao.saveLoanApplication(loanApplication);
                    return "Not eligible: EMIs exceed 75% of income";
                }

                BigDecimal interestRate = new BigDecimal(0);
                if (status.equals("GOOD")) {
                    interestRate = new BigDecimal("10.5");
                } else {
                    interestRate = new BigDecimal("10.75");
                }

                Integer loanTenureInMonths = loanApplication.getLoanTermInMonths();

                BigDecimal eligiblePrincipalAmount = LoanCalculator.calculatePrincipal(effordableAmountPerMonth, interestRate, loanTenureInMonths);

                if (eligiblePrincipalAmount.compareTo(new BigDecimal(50000)) < 0) {
                    loanApplication.setStatus(Status.REJECTED);
                    loanApplication.setActive(false);
                    loanDetailsDao.saveLoanApplication(loanApplication);
                    return "Cannot approve loan because it is below 50000";
                }
//                BigDecimal finalEligibleLoanAmount = BigDecimal.ZERO;
//                if (eligiblePrincipalAmount.compareTo(loanApplication.getLoanAmount()) <= 0) {
//                    finalEligibleLoanAmount = eligiblePrincipalAmount;
//                } else {
//                    finalEligibleLoanAmount = loanApplication.getLoanAmount();
//                }

                loanApplication.setStatus(Status.IN_PROCESS);
                loanApplication.setApprovalAmount(eligiblePrincipalAmount);
                LoanApplication updatedLoanEntity = loanDetailsDao.saveLoanApplication(loanApplication);;
                if (updatedLoanEntity.getApplicationId() != null) {
                    return "Loan Approved amount inserted";
                }
            } else {
                Status loanStatus = Status.REJECTED;
                LoanApplication updatedLoanEntity = loanDetailsDao.updateLoanApplication(Integer.parseInt(loanApplicationId), null, loanStatus);
                return "Cibil score is Poor loan cannot be provided";
            }
        } else {
            return "Application Not found";
        }
        return "Error";
    }
}
