package su.L09XmlLab.service;


import su.L09XmlLab.model.dto.addresses.CreateAddressDTO;
import su.L09XmlLab.model.entity.Address;

public interface AddressService {
    Address create(CreateAddressDTO addresData);
}
