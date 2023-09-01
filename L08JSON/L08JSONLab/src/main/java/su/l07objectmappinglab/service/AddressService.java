package su.l07objectmappinglab.service;

import su.l07objectmappinglab.model.dto.addresses.CreateAddressDTO;
import su.l07objectmappinglab.model.entity.Address;

public interface AddressService {
    Address create(CreateAddressDTO addresData);
}
