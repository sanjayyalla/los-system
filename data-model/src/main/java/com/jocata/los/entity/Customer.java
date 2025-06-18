package com.jocata.los.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "aadhar_number")
    private String aadharNumber;
    @Column(name = "pan_number")
    private String panNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private IncomeDetails incomeDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<LoanApplication> loanApplications;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private AadharDetails aadharDetails;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private PanDetails panDetails;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CibilDetails cibilDetails;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public List<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IncomeDetails getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(IncomeDetails incomeDetails) {
        this.incomeDetails = incomeDetails;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public AadharDetails getAadharDetails() {
        return aadharDetails;
    }

    public void setAadharDetails(AadharDetails aadharDetails) {
        this.aadharDetails = aadharDetails;
    }

    public PanDetails getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(PanDetails panDetails) {
        this.panDetails = panDetails;
    }

    public CibilDetails getCibilDetails() {
        return cibilDetails;
    }

    public void setCibilDetails(CibilDetails cibilDetails) {
        this.cibilDetails = cibilDetails;
    }
}
