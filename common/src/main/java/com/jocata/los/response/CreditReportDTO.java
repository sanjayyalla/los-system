package com.jocata.los.response;

import java.util.List;

public class CreditReportDTO {

    private CibilScoreDTO cibilScore;
    private List<AccountDTO> accounts;
    private List<EnquiryDTO> enquiries;

    public CibilScoreDTO getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScoreDTO cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<EnquiryDTO> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<EnquiryDTO> enquiries) {
        this.enquiries = enquiries;
    }
}
