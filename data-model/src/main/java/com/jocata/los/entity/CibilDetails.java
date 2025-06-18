package com.jocata.los.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "cibil_details")
public class CibilDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cibil_id")
    private Integer cibilId;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "cibil_score")
    private Integer cibilScore;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    @Column(name = "credit_status")
    private String creditStatus;

    @Column(name = "no_of_enquiries")
    private Integer noOfEnquiries;

    @Column(name = "no_of_active_accounts")
    private Integer noOfActiveAccounts;

    @Column(name = "loan_outstanding")
    private BigDecimal loanOutstanding;

    @Column(name = "emis_total")
    private BigDecimal emisTotal;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    public Integer getCibilId() {
        return cibilId;
    }

    public void setCibilId(Integer cibilId) {
        this.cibilId = cibilId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(Integer cibilScore) {
        this.cibilScore = cibilScore;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getNoOfEnquiries() {
        return noOfEnquiries;
    }

    public void setNoOfEnquiries(Integer noOfEnquiries) {
        this.noOfEnquiries = noOfEnquiries;
    }

    public Integer getNoOfActiveAccounts() {
        return noOfActiveAccounts;
    }

    public void setNoOfActiveAccounts(Integer noOfActiveAccounts) {
        this.noOfActiveAccounts = noOfActiveAccounts;
    }

    public BigDecimal getLoanOutstanding() {
        return loanOutstanding;
    }

    public void setLoanOutstanding(BigDecimal loanOutstanding) {
        this.loanOutstanding = loanOutstanding;
    }

    public BigDecimal getEmisTotal() {
        return emisTotal;
    }

    public void setEmisTotal(BigDecimal emisTotal) {
        this.emisTotal = emisTotal;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}