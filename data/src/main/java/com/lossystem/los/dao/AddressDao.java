package com.lossystem.los.dao;

import com.lossystem.los.entity.Address;

public interface AddressDao {
    Address saveAddress(Address address);
    Address getAddress(Integer addressId);
}
