package com.jocata.los.response;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDTO {
    private String accountNumber;
    private String accountType;
    private String memberName;
    private String ownership;
    private String dateOpened;
    private String lastPaymentDate;
    private String currentBalance;
    private String creditLimit;
    private String sanctionedAmount;
    private String emiAmount;
    private String tenureMonths;
    private String paymentHistory;
    private String accountStatus;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getSanctionedAmount() {
        return sanctionedAmount;
    }

    public void setSanctionedAmount(String sanctionedAmount) {
        this.sanctionedAmount = sanctionedAmount;
    }

    public String getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(String emiAmount) {
        this.emiAmount = emiAmount;
    }

    public String getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(String tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public String getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(String paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
