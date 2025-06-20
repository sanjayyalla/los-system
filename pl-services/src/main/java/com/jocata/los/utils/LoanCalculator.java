package com.jocata.los.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LoanCalculator {
    public static BigDecimal calculatePrincipal(BigDecimal emi, BigDecimal annualInterestRate, int tenureMonths) {
        MathContext mc = new MathContext(15, RoundingMode.HALF_EVEN);

        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12 * 100), mc);

        if (monthlyRate.compareTo(BigDecimal.ZERO) == 0) {
            return emi.multiply(BigDecimal.valueOf(tenureMonths), mc);
        }

        BigDecimal one = BigDecimal.ONE;
        BigDecimal factor = one.add(monthlyRate).pow(tenureMonths, mc);

        BigDecimal numerator = emi.multiply(factor.subtract(one), mc);
        BigDecimal denominator = monthlyRate.multiply(factor, mc);

        return numerator.divide(denominator, 2, RoundingMode.HALF_EVEN);
    }
}
