package com.jocata.los.response.updateCibilData;

import java.util.List;

public class CreditReportDTOForCibilUpdate {
    private CustomerDTO customer;
    private CibilScoreDTO cibilScore;
    private List<AccountDTO> accounts;
//    private List<String> remarks;


    public CibilScoreDTO getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScoreDTO cibilScore) {
        this.cibilScore = cibilScore;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }


}
