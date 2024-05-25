package org.softuni.service;


import org.softuni.model.dto.CreateAddressDTO;
import org.softuni.model.entity.Address;

public interface AddressService {
    Address create(CreateAddressDTO addressData);
}
