package com.jocata.los.service.impl;

import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.form.EMI;
import com.jocata.los.response.updateCibilData.AccountDTO;
import com.jocata.los.response.updateCibilData.CreditReportDTOForCibilUpdate;
import com.jocata.los.response.updateCibilData.CustomerDTO;
import com.jocata.los.service.CibilService;
import com.jocata.los.service.LoanDispersmentService;
import com.jocata.los.service.UpdateCibilDataService;
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
