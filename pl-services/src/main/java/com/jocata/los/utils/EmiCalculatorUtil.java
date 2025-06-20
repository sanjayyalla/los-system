package com.jocata.los.utils;

import com.jocata.los.form.EMI;

import java.util.ArrayList;
import java.util.List;

public class EmiCalculatorUtil {
    public static List<EMI> calculateEmi(double principal, double annualRate, int termMonths){

        List<EMI> emiList = new ArrayList<>();
        double monthlyRate = annualRate / (12 * 100);
        double emiAmount = (principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
                (Math.pow(1 + monthlyRate, termMonths) - 1);
        double remainingPrincipal = principal;

        for (int i = 1; i <= termMonths; i++) {
            EMI emi = new EMI();
            double interest = remainingPrincipal * monthlyRate;
            double principalComponent = emiAmount - interest;

            emi.setInterest(round(interest));
            emi.setPrincipalComponent(round(principalComponent));
            emi.setTermMonths(termMonths);
            emi.setPricipalAmount(principal);
            remainingPrincipal -= principalComponent;

            emiList.add(emi);
        }

        return emiList;
    }
    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
