package su.l07objectmappinglab.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.l07objectmappinglab.model.dto.CreateAddressDTO;
import su.l07objectmappinglab.model.entity.Address;
import su.l07objectmappinglab.repository.AddressRepository;
import su.l07objectmappinglab.service.AddressService;


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
