package com.jocata.los.service;

import com.jocata.los.form.EMI;

import java.util.List;

public interface LoanDispersmentService {
    List<EMI> getEmisList(String loanApplicationId);
}
