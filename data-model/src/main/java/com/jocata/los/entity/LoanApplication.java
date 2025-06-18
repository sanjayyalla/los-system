package com.jocata.los.entity;

import com.jocata.los.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer applicationId;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "loan_amount")
    private BigDecimal loanAmount;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "approval_amount")
    private BigDecimal approvalAmount;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "loan_term_in_months")
    private Integer loanTermInMonths;

    @Column(name = "loan_purpose")
    private String loanPurpose;

    public Integer getLoanTermInMonths() {
        return loanTermInMonths;
    }

    public void setLoanTermInMonths(Integer loanTermInMonths) {
        this.loanTermInMonths = loanTermInMonths;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getApprovalAmount() {
        return approvalAmount;
    }

    public void setApprovalAmount(BigDecimal approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "applicationId=" + applicationId +
                ", customer=" + customer +
                ", loanAmount=" + loanAmount +
                ", loanType='" + loanType + '\'' +
                ", applicationDate=" + applicationDate +
                ", status=" + status +
                ", approvalAmount=" + approvalAmount +
                ", isActive=" + isActive +
                ", loanTermInMonths=" + loanTermInMonths +
                ", loanPurpose='" + loanPurpose + '\'' +
                '}';
    }
}
