package com.jocata.los.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EnquiryDTO {
    private String enquiryDate;
    private String memberName;
    private String enquiryPurpose;
    private String enquiryAmount;

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(String enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEnquiryPurpose() {
        return enquiryPurpose;
    }

    public void setEnquiryPurpose(String enquiryPurpose) {
        this.enquiryPurpose = enquiryPurpose;
    }

    public String getEnquiryAmount() {
        return enquiryAmount;
    }

    public void setEnquiryAmount(String enquiryAmount) {
        this.enquiryAmount = enquiryAmount;
    }
}
