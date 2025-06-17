package com.jocata.los.dao;

import com.jocata.los.entity.Customer;
import com.jocata.los.form.CustomerForm;

public interface CustomerDao {
    Customer saveCustomer(Customer customer);

    Customer getCustomer(Integer customerId);
}
