package com.lossystem.los.service.impl;

import com.lossystem.los.dao.LoanDetailsDao;
import com.lossystem.los.entity.CibilDetails;
import com.lossystem.los.entity.LoanApplication;
import com.lossystem.los.enums.Status;
import com.lossystem.los.form.EMI;
import com.lossystem.los.service.LoanDispersmentService;
import com.lossystem.los.utils.EmiCalculatorUtil;
import com.lossystem.los.utils.LoanCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDispersmentServiceImpl implements LoanDispersmentService {

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Override
    public List<EMI> getEmisList(String loanApplicationId) {

        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(Integer.parseInt(loanApplicationId));
        if (!loanApplication.getStatus().equals(Status.REJECTED)) {
            int tenureMonths = loanApplication.getLoanTermInMonths();
            String status = loanApplication.getCustomer().getCibilDetails().getCreditStatus();
            double interestRate = status.equalsIgnoreCase("Good") ? 10.5 : 10.75;
            double principalAmount = loanApplication.getApprovalAmount().doubleValue();
            return EmiCalculatorUtil.calculateEmi(principalAmount, interestRate, tenureMonths);
        }

        return null;
    }
}
