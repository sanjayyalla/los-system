package com.jocata.los.form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanApplicationForm {
    @JsonProperty("customer")
    private CustomerForm customerForm;
    @JsonProperty("loanDetails")
    private LoanDetailsForm loanDetailsForm;
    @JsonProperty("incomeDetails")
    private IncomeDetailsForm incomeDetailsForm;

    public CustomerForm getCustomerForm() {
        return customerForm;
    }

    public void setCustomerForm(CustomerForm customerForm) {
        this.customerForm = customerForm;
    }

    public LoanDetailsForm getLoanDetailsForm() {
        return loanDetailsForm;
    }

    public void setLoanDetailsForm(LoanDetailsForm loanDetailsForm) {
        this.loanDetailsForm = loanDetailsForm;
    }

    public IncomeDetailsForm getIncomeDetailsForm() {
        return incomeDetailsForm;
    }

    public void setIncomeDetailsForm(IncomeDetailsForm incomeDetailsForm) {
        this.incomeDetailsForm = incomeDetailsForm;
    }
}
