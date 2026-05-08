package com.lossystem.los.service.impl;

import com.lossystem.los.dao.LoanDetailsDao;
import com.lossystem.los.entity.LoanApplication;
import com.lossystem.los.form.EMI;
import com.lossystem.los.response.updateCibilData.AccountDTO;
import com.lossystem.los.response.updateCibilData.CreditReportDTOForCibilUpdate;
import com.lossystem.los.response.updateCibilData.CustomerDTO;
import com.lossystem.los.service.CibilService;
import com.lossystem.los.service.LoanDispersmentService;
import com.lossystem.los.service.UpdateCibilDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateCibilDataServiceImpl implements UpdateCibilDataService {

    @Autowired
    private LoanDispersmentService loanDispersmentService;

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Autowired
    private CibilService cibilService;

    @Override
    public String updateAccountDetails(String loanApplicationId) {

        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(Integer.parseInt(loanApplicationId));
        List<EMI> emiList = loanDispersmentService.getEmisList(loanApplicationId);
        double emiAmount = emiList.get(0).getPrincipalComponent() + emiList.get(0).getInterest();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPanNumber(loanApplication.getCustomer().getPanNumber());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(loanApplicationId);
        accountDTO.setAccountType(loanApplication.getLoanType());
        accountDTO.setMemberName("JPMorgan Chase");
        accountDTO.setOwnership("Individual");
        accountDTO.setDateOpened(Date.valueOf(LocalDate.now()));
        accountDTO.setLastPaymentDate(null);
        accountDTO.setCurrentBalance(loanApplication.getApprovalAmount());
        accountDTO.setCreditLimit(null);
        accountDTO.setSanctionedAmount(loanApplication.getApprovalAmount());
        accountDTO.setEmiAmount(BigDecimal.valueOf(emiAmount));
        accountDTO.setTenureMonths(loanApplication.getLoanTermInMonths());
        accountDTO.setPaymentHistory(null);
        String status = String.valueOf(loanApplication.getActive()).equalsIgnoreCase("true") ?"Active":"In Active";
//        String status = Boolean.TRUE.equals(loanApplication.getActive()) ? "Active" : "In Active";

        accountDTO.setAccountStatus(status);

        List<AccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(accountDTO);
        CreditReportDTOForCibilUpdate creditReportDTO = new CreditReportDTOForCibilUpdate();
        creditReportDTO.setCustomer(customerDTO);
        creditReportDTO.setAccounts(accountDTOList);

        CreditReportDTOForCibilUpdate updatedDTO = cibilService.postCibilDetails(creditReportDTO);
        if (updatedDTO != null) {
            return "Added";
        }
        return "Failed";
    }
}
