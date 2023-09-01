package su.l07objectmappinglab.service;

import su.l07objectmappinglab.model.dto.CreateAddressDTO;
import su.l07objectmappinglab.model.entity.Address;

public interface AddressService {
    Address create(CreateAddressDTO addresData);
}
