package com.jocata.los.service.impl;

import com.jocata.los.dao.CustomerDao;
import com.jocata.los.entity.Address;
import com.jocata.los.entity.Customer;
import com.jocata.los.form.AddressForm;
import com.jocata.los.form.CustomerForm;
import com.jocata.los.form.LoanApplicationForm;
import com.jocata.los.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class LoanApplicationServiceImpl implements LoanApplicationService {


    @Autowired
    private CustomerDao customerDao;

    @Override
    public LoanApplicationForm createLoanApplication(LoanApplicationForm form) {
        Customer customer = setCustomer(form.getCustomerForm(), form.getCustomerForm().getAddress());

    }

    private Customer setCustomer(CustomerForm customerForm, AddressForm addressForm) {
        Customer customer = new Customer();
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setAadharNumber(customerForm.getAadharNumber());
        customer.setPanNumber(customerForm.getPanNumber());
        customer.setDob(LocalDate.parse(customerForm.getDob()));


        Address address = new Address();
        address.setCity(addressForm.getCity());
        address.setCountry(addressForm.getCountry());
        address.setState(addressForm.getState());
        address.setStreet(addressForm.getStreet());
        address.setZipCode(addressForm.getZipCode());

        address.setCustomer(customer);
        customer.setAddress(address);


    }
}
