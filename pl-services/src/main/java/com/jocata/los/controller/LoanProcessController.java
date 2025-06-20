package com.jocata.los.controller;

import com.jocata.los.service.LoanProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v4")
public class LoanProcessController {

    @Autowired
    private LoanProcessService loanProcessService;

    @GetMapping("/approveLoan")
    public String approveLoan(@RequestParam String loanApplicationId,@RequestParam String requestedAmount){
        if(loanApplicationId!=null && !loanApplicationId.isEmpty()){
            return loanProcessService.approveLoan(loanApplicationId,requestedAmount);
        }
        return "Please Enter Loan application ID";
    }

    @GetMapping("/rejectLoan")
    public String rejectLoan(@RequestParam String loanApplicationId){
        if(loanApplicationId!=null && !loanApplicationId.isEmpty()){
            return loanProcessService.rejectLoan(loanApplicationId);
        }
        return "Please Enter Loan application ID";
    }

}
