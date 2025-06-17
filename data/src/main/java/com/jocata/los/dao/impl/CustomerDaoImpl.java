package com.jocata.los.dao.impl;

import com.jocata.los.dao.CustomerDao;
import com.jocata.los.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer saveCustomer(Customer customer) {

        if (customer.getCustomerId() == null) {
            entityManager.persist(customer);
        } else {
            customer = entityManager.merge(customer);
        }
        return customer;
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        return entityManager.find(Customer.class, customerId);
    }


}
