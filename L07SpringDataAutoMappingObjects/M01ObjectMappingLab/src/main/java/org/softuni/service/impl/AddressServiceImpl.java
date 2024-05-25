package org.softuni.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.model.dto.CreateAddressDTO;
import org.softuni.model.entity.Address;
import org.softuni.repository.AddressRepository;
import org.softuni.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private final ModelMapper mapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public Address create(CreateAddressDTO addressData) {
       Address address = mapper.map(addressData,Address.class);
       return  this.addressRepository.save(address);

    }
}
