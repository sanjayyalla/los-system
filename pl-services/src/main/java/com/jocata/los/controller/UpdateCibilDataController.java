package com.jocata.los.controller;

import com.jocata.los.service.UpdateCibilDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v6")
public class UpdateCibilDataController {

    @Autowired
    private UpdateCibilDataService updateCibilDataService;

    @GetMapping("/updateAccountDetails")
    public String updateAccountDetails(@RequestParam String loanApplicationId) {
        if(loanApplicationId!=null && !loanApplicationId.isEmpty()){
            return updateCibilDataService.updateAccountDetails(loanApplicationId);
        }
        return "Provide Application ID";
    }
}
