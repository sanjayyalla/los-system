package com.lossystem.los.dao;

import com.lossystem.los.entity.Customer;
import com.lossystem.los.form.CustomerForm;

public interface CustomerDao {
    Customer saveCustomer(Customer customer);

    Customer getCustomer(Integer customerId);
}
