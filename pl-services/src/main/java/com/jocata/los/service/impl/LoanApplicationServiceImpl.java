package com.jocata.los.service.impl;

import com.jocata.los.dao.AddressDao;
import com.jocata.los.dao.CustomerDao;
import com.jocata.los.dao.IncomeDetailsDao;
import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.Address;
import com.jocata.los.entity.Customer;
import com.jocata.los.entity.IncomeDetails;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.enums.Status;
import com.jocata.los.form.*;
import com.jocata.los.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {


    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private IncomeDetailsDao incomeDetailsDao;

    @Autowired
    private LoanDetailsDao loanDetailsDao;

    @Override
    public LoanApplicationForm createLoanApplication(LoanApplicationForm form) {
        Customer customer = setCustomer(form.getCustomerForm());
        Address address = setAddress(form.getCustomerForm().getAddress(), customer);
        IncomeDetails incomeDetails = setIncomeDetails(form.getIncomeDetailsForm(), customer);
        LoanDetailsForm loanDetailsForm = form.getLoanDetailsForm();

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setCustomer(customer);
        loanApplication.setLoanAmount(new BigDecimal(loanDetailsForm.getLoanAmount()));
        loanApplication.setLoanType(loanDetailsForm.getLoanType());
        loanApplication.setApplicationDate(LocalDate.now());
//        loanApplication.setStatus(Status.valueOf(loanDetailsForm.getStatus()));
        loanApplication.setStatus(Status.PENDING);
        loanApplication.setLoanTermInMonths(Integer.valueOf(loanDetailsForm.getLoanTermInMonths()));
        loanApplication.setLoanPurpose(loanDetailsForm.getLoanPurpose());
        loanApplication.setActive(true);

        loanApplication = loanDetailsDao.saveLoanApplication(loanApplication);

        LoanApplicationForm newForm = entityToForm(customer, address, incomeDetails, loanApplication);
        return newForm;
    }

    @Override
    public LoanApplicationForm getLoanApplicationById(Integer loanApplicationId) {
        LoanApplication loanApplication = loanDetailsDao.getLoanApplicationById(loanApplicationId);
        if (loanApplication == null) {
            return null;
        }
        Customer customer = loanApplication.getCustomer();
        if (customer == null) {
            return null;
        }
        Address address = customer.getAddress();
        IncomeDetails incomeDetails = customer.getIncomeDetails();

        return entityToForm(customer, address, incomeDetails, loanApplication);
    }

    private LoanApplicationForm entityToForm(Customer customer, Address address, IncomeDetails incomeDetails, LoanApplication loanApplication) {
        CustomerForm customerForm = new CustomerForm();
        customerForm.setCustomerId(String.valueOf(customer.getCustomerId()));
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setEmail(customer.getEmail());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setAadharNumber(customer.getAadharNumber());
        customerForm.setPanNumber(customer.getPanNumber());
        customerForm.setDob(String.valueOf(customer.getDob()));
        customerForm.setCreatedAt(String.valueOf(customer.getCreatedAt()));

        AddressForm addressForm = new AddressForm();
        addressForm.setAddressId(String.valueOf(address.getAddressId()));
        addressForm.setStreet(address.getStreet());
        addressForm.setCity(address.getCity());
        addressForm.setZipCode(address.getZipCode());
        addressForm.setState(address.getState());
        addressForm.setCountry(address.getCountry());

        customerForm.setAddress(addressForm);

        IncomeDetailsForm incomeDetailsForm = new IncomeDetailsForm();
        incomeDetailsForm.setEmployerName(incomeDetails.getEmployerName());
        incomeDetailsForm.setMonthlyIncome(String.valueOf(incomeDetails.getMonthlyIncome()));
        incomeDetailsForm.setEmploymentStatus(incomeDetails.getEmploymentStatus());
        incomeDetailsForm.setYearsAtJob(String.valueOf(incomeDetails.getYearsAtJob()));

        LoanDetailsForm loanDetailsForm = new LoanDetailsForm();
        loanDetailsForm.setLoanAmount(String.valueOf(loanApplication.getLoanAmount()));
        loanDetailsForm.setLoanType(loanApplication.getLoanType());
        loanDetailsForm.setApplicationDate(String.valueOf(loanApplication.getApplicationDate()));
        loanDetailsForm.setStatus(String.valueOf(loanApplication.getStatus()));
        loanDetailsForm.setApprovalAmount(String.valueOf(loanApplication.getApprovalAmount()));
        loanDetailsForm.setIsActive(String.valueOf(loanApplication.getActive()));
        loanDetailsForm.setLoanTermInMonths(String.valueOf(loanApplication.getLoanTermInMonths()));
        loanDetailsForm.setLoanPurpose(loanApplication.getLoanPurpose());

        LoanApplicationForm newLoanApplicationForm = new LoanApplicationForm();
        newLoanApplicationForm.setCustomerForm(customerForm);
        newLoanApplicationForm.setLoanDetailsForm(loanDetailsForm);
        newLoanApplicationForm.setIncomeDetailsForm(incomeDetailsForm);

        return newLoanApplicationForm;
    }

    private IncomeDetails setIncomeDetails(IncomeDetailsForm incomeForm, Customer customer) {
        IncomeDetails incomeDetails = new IncomeDetails();
        incomeDetails.setMonthlyIncome(new BigDecimal(incomeForm.getMonthlyIncome()));
        incomeDetails.setEmploymentStatus(incomeForm.getEmploymentStatus());
        incomeDetails.setEmployerName(incomeForm.getEmployerName());
        incomeDetails.setYearsAtJob(Integer.parseInt(incomeForm.getYearsAtJob()));
        incomeDetails.setCustomer(customer);
        return incomeDetailsDao.saveIncomeDetails(incomeDetails);
    }

    private Address setAddress(AddressForm addressForm, Customer customer) {
        Address address = new Address();
        address.setStreet(addressForm.getStreet());
        address.setCity(addressForm.getCity());
        address.setState(addressForm.getState());
        address.setZipCode(addressForm.getZipCode());
        address.setCountry(addressForm.getCountry());
        address.setCustomer(customer);
        return addressDao.saveAddress(address);
    }

    private Customer setCustomer(CustomerForm customerForm) {

        Customer customer = new Customer();
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setAadharNumber(customerForm.getAadharNumber());
        customer.setPanNumber(customerForm.getPanNumber());
        customer.setDob(LocalDate.parse(customerForm.getDob()));
        return customerDao.saveCustomer(customer);
    }
}
