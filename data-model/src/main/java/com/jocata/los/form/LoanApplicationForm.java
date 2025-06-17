package com.jocata.los.form;

public class LoanApplicationForm {

    private CustomerForm customerForm;
    private LoanDetailsForm loanDetailsForm;
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
