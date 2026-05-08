package com.lossystem.los.service;

import com.lossystem.los.form.EMI;

import java.util.List;

public interface LoanDispersmentService {
    List<EMI> getEmisList(String loanApplicationId);
}
