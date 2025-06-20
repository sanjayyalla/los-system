package com.jocata.los.controller;

import com.jocata.los.form.EMI;
import com.jocata.los.service.LoanDispersmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class LoanDispersmentController {

    @Autowired
    private LoanDispersmentService loanDispersmentService;

    @GetMapping("/getEmisList")
    public List<EMI> getEmisList(@RequestParam String loanApplicationId) {
        if (loanApplicationId != null && !loanApplicationId.isEmpty()) {
            return loanDispersmentService.getEmisList(loanApplicationId);
        }
        return null;
    }
}
