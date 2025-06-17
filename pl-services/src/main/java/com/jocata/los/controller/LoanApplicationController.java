package com.jocata.los.controller;

import com.jocata.los.form.LoanApplicationForm;
import com.jocata.los.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService service;

    @PostMapping("/createLoanApplication")
    public LoanApplicationForm createLoanApplication(@RequestBody LoanApplicationForm form) {
        LoanApplicationForm loanApplicationForm = null;
        try {
            loanApplicationForm = service.createLoanApplication(form);
            if (loanApplicationForm != null) {
                return loanApplicationForm;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
