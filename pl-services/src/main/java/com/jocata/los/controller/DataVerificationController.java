package com.jocata.los.controller;

import com.jocata.los.service.DataVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DataVerificationController {

    @Autowired
    private DataVerificationService dataVerificationService;

    @GetMapping("/getCustomerData")
    public String getCustomerData(@RequestParam String loanApplicationId) {
        return dataVerificationService.getCustomerData(loanApplicationId);
    }
}
