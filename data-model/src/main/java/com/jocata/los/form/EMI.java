package com.jocata.los.form;

public class EMI {
    int termMonths;
    double pricipalAmount;
    double interest;
    double principalComponent;

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public double getPricipalAmount() {
        return pricipalAmount;
    }

    public void setPricipalAmount(double pricipalAmount) {
        this.pricipalAmount = pricipalAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrincipalComponent() {
        return principalComponent;
    }

    public void setPrincipalComponent(double principalComponent) {
        this.principalComponent = principalComponent;
    }
}
