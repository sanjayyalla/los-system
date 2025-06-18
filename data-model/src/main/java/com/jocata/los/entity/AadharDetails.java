package com.jocata.los.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "aadhar_details")
public class AadharDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aadhar_id")
    private Integer aadharId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "aadhar_number", unique = true)
    private String aadharNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    public Integer getAadharId() {
        return aadharId;
    }

    public void setAadharId(Integer aadharId) {
        this.aadharId = aadharId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    // Getters and Setters
    // ...
}
