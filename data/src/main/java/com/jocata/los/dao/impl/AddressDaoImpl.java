package com.jocata.los.dao.impl;

import com.jocata.los.dao.AddressDao;
import com.jocata.los.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {


    @Autowired
    private EntityManager entityManager;

    @Override
    public Address saveAddress(Address address) {
        if (address.getAddressId() == null) {
            entityManager.persist(address);
        } else {
            address = entityManager.merge(address);
        }
        return address;
    }

    @Override
    public Address getAddress(Integer addressId) {
        return entityManager.find(Address.class, addressId);
    }
}
