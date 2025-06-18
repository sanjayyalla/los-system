package com.jocata.los.service.impl;

import com.jocata.los.dao.DataVerificationDao;
import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.AadharDetails;
import com.jocata.los.entity.CibilDetails;
import com.jocata.los.entity.Customer;
import com.jocata.los.entity.PanDetails;
import com.jocata.los.response.AadharResponseForm;
import com.jocata.los.response.AccountDTO;
import com.jocata.los.response.CreditReportDTO;
import com.jocata.los.response.PanResponseForm;
import com.jocata.los.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DataVerificationServiceImpl implements DataVerificationService {

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Autowired
    private AadharService aadharService;

    @Autowired
    private PanService panService;

    @Autowired
    private CibilService cibilService;

    @Autowired
    private DataVerificationDao dataVerificationDao;

    @Override
    public String getCustomerData(String loanApplicationId) {
        Customer customer = loanDetailsDao.getLoanApplicationById(Integer.parseInt(loanApplicationId)).getCustomer();
        if (customer.getAadharNumber() != null && customer.getPanNumber() != null) {
            String panNumber = customer.getPanNumber();
            PanResponseForm responseForm = panService.getPanDetailsByPanNumber(panNumber);
            PanDetails panDetails = savePanDetails(responseForm, customer);
            if (panDetails.getPanId() != null) {
                String aadharNumber = customer.getAadharNumber();
                System.out.println(aadharNumber);
                AadharResponseForm aadharResponseForm = aadharService.getAadharDetailsByAadharNumber(aadharNumber);
                AadharDetails aadharDetails = saveAadharDetails(aadharResponseForm, customer);
                if (aadharDetails.getAadharId() != null) {
                    CreditReportDTO creditReportDTO = cibilService.getCibilDetailsByPanNumber(panNumber);
                    CibilDetails cibilDetails = saveCibilDetails(creditReportDTO, customer);
                    if(cibilDetails.getCibilId()!=null){
                        return "Success";
                    }
                }
            } else {
                return "Failure";
            }

        }
        return "Error";
    }

    private CibilDetails saveCibilDetails(CreditReportDTO creditReportDTO, Customer customer) {

        CibilDetails cibilDetails = new CibilDetails();
        cibilDetails.setCustomer(customer);
        cibilDetails.setCibilScore(Integer.valueOf(creditReportDTO.getCibilScore().getScore()));
        cibilDetails.setLastUpdated(LocalDate.now());
        Integer cibilScore = Integer.parseInt(creditReportDTO.getCibilScore().getScore());
        if (cibilScore < 720) {
            cibilDetails.setCreditStatus("POOR");
        } else if (cibilScore <= 750) {
            cibilDetails.setCreditStatus("AVERAGE");
        } else {
            cibilDetails.setCreditStatus("GOOD");
        }
        cibilDetails.setNoOfEnquiries(creditReportDTO.getEnquiries().size());

        BigDecimal loanOutStanding = new BigDecimal(0);
        Integer activeAccountsCount = 0;
        for (AccountDTO accountDTO : creditReportDTO.getAccounts()) {
            if (accountDTO.getAccountStatus().equals("Active")) {
                activeAccountsCount += 1;
            }
            loanOutStanding = loanOutStanding.add(new BigDecimal(accountDTO.getCurrentBalance()));
        }
        cibilDetails.setNoOfActiveAccounts(activeAccountsCount);
        cibilDetails.setLoanOutstanding(loanOutStanding);

        return dataVerificationDao.saveCibilDetails(cibilDetails);
    }

    private AadharDetails saveAadharDetails(AadharResponseForm responseForm, Customer customer) {
        AadharDetails aadharDetails = new AadharDetails();
        aadharDetails.setCustomer(customer);
        aadharDetails.setAadharNumber(responseForm.getAadharDetails().getNumber());
        aadharDetails.setFullName(responseForm.getAadharDetails().getName());
        aadharDetails.setGender(responseForm.getAadharDetails().getGender());
        aadharDetails.setDob(LocalDate.parse(responseForm.getAadharDetails().getDob()));
        aadharDetails.setFatherName(responseForm.getAadharDetails().getFatherName());
        aadharDetails.setStreet(responseForm.getAadharDetails().getAddress().getStreet());
        aadharDetails.setCity(responseForm.getAadharDetails().getAddress().getCity());
        aadharDetails.setState(responseForm.getAadharDetails().getAddress().getState());
        aadharDetails.setPincode(responseForm.getAadharDetails().getAddress().getPincode());

        return dataVerificationDao.saveAadharDetails(aadharDetails);
    }

    private PanDetails savePanDetails(PanResponseForm responseForm, Customer customer) {
        PanDetails panDetails = new PanDetails();
        panDetails.setCustomer(customer);
        panDetails.setPanNumber(responseForm.getPanDetails().getPanNumber());
        panDetails.setFullName(responseForm.getPanDetails().getFullName());
        panDetails.setDob(LocalDate.parse(responseForm.getPanDetails().getDob()));
        panDetails.setFatherName(responseForm.getPanDetails().getFatherName());
        panDetails.setGender(responseForm.getPanDetails().getGender());
        panDetails.setStatus(responseForm.getPanDetails().getStatus());
        panDetails.setIssuedOn(LocalDate.parse(responseForm.getPanDetails().getIssuedOn()));
        panDetails.setCategory(responseForm.getPanDetails().getCategory());
        panDetails.setAddress(responseForm.getPanDetails().getAddress());

        return dataVerificationDao.savePanDetails(panDetails);
    }
}
