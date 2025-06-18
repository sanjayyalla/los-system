package com.jocata.los.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pan_details")
public class PanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pan_id")
    private Integer panId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "pan_number", unique = true)
    private String panNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "status")
    private String status;

    @Column(name = "issued_on")
    private LocalDate issuedOn;

    @Column(name = "category")
    private String category;

    @Column(name = "address")
    private String address;

    public Integer getPanId() {
        return panId;
    }

    public void setPanId(Integer panId) {
        this.panId = panId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
