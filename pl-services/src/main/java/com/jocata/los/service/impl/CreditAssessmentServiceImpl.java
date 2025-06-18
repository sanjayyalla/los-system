package com.jocata.los.service.impl;

import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.CibilDetails;
import com.jocata.los.entity.Customer;
import com.jocata.los.entity.IncomeDetails;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.response.AccountDTO;
import com.jocata.los.response.CreditReportDTO;
import com.jocata.los.response.EnquiryDTO;
import com.jocata.los.service.CibilService;
import com.jocata.los.service.CreditAssessmentService;
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
                String panNumber = customer.getPanNumber();
                CreditReportDTO creditReportDTO = cibilService.getCibilDetailsByPanNumber(panNumber);
                IncomeDetails incomeDetails = loanApplication.getCustomer().getIncomeDetails();
                CibilDetails cibilDetails = loanApplication.getCustomer().getCibilDetails();
                int accountCount = cibilDetails.getNoOfActiveAccounts();
                if (accountCount > 5) {
                    return "Existing active accounts are greater than 5, Loan cannot be approved";
                }
                int enquiriesCount = cibilDetails.getNoOfEnquiries();
                if (enquiriesCount > 5) {
                    return "Enquiries are greater than 5 ,Loan cannot be approved";
                }
//                int accountCount = 0;
//                for (AccountDTO accountDTO : creditReportDTO.getAccounts()) {
//                    if (accountDTO.getAccountStatus().equals("Active")) {
//                        accountCount++;
//                    }
//                }
//                if (accountCount > 5) {
//                    return "Existing active accounts are greater than 5, Loan cannot be approved";
//                }
//                int enquiriesCount = creditReportDTO.getEnquiries().size();
//                if (enquiriesCount > 5) {
//                    return "Enquiries are greater than 5 ,Loan cannot be approved";
//                }

                BigDecimal effordableAmountPerMonth = incomeDetails.getMonthlyIncome().multiply(new BigDecimal("0.75")).subtract(cibilDetails.getEmisTotal());
                if (effordableAmountPerMonth.compareTo(BigDecimal.ZERO) <= 0) {
                    return "Not eligible: EMIs exceed 75% of income";
                }
                System.out.println("Effordable per month : " + effordableAmountPerMonth);
                BigDecimal interestAmount = new BigDecimal(0);
                if (status.equals("GOOD")) {
                    interestAmount = loanApplication.getLoanAmount().multiply(new BigDecimal("0.105"));
                } else {
                    interestAmount = loanApplication.getLoanAmount().multiply(new BigDecimal("0.1075"));
                }
                System.out.println("Interest amount :" + interestAmount);
                BigDecimal amountToBePaid = loanApplication.getLoanAmount().add(interestAmount);
                System.out.println("Loan amount to be paid : " + amountToBePaid);
                BigDecimal effordableAmountForTenure = effordableAmountPerMonth.multiply(BigDecimal.valueOf(loanApplication.getLoanTermInMonths()));
                System.out.println("Loan effordableAmountForTenure : " + effordableAmountForTenure);
                BigDecimal approvalAmount;

                if (amountToBePaid.compareTo(effordableAmountForTenure) <= 0) {
                    approvalAmount = loanApplication.getLoanAmount();
                } else {
                    approvalAmount = effordableAmountForTenure;
                }
                System.out.println("Approval:----" + approvalAmount);
                LoanApplication updatedLoanEntity = loanDetailsDao.updateLoanApplication(Integer.parseInt(loanApplicationId), approvalAmount);
                if (updatedLoanEntity.getApplicationId() != null) {
                    return "Loan Approved amount inserted";
                }
            } else {
                return "Cibil score is Poor loan cannot be provided";
            }
        } else {
            return "Application Not found";
        }
        return "Error";
    }
}
