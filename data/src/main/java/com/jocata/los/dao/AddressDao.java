package com.jocata.los.dao;

import com.jocata.los.entity.Address;

public interface AddressDao {
    Address saveAddress(Address address);
    Address getAddress(Integer addressId);
}
