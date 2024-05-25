package org.softuni.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.model.dto.CreateEmployeeDTO;
import org.softuni.model.dto.EmployeeDTO;
import org.softuni.model.dto.EmployeeNameSalaryDTO;
import org.softuni.model.entity.Address;
import org.softuni.model.entity.Employee;
import org.softuni.repository.AddressRepository;
import org.softuni.repository.EmployeeRepository;
import org.softuni.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final AddressRepository addressRepository;

    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeData) {

        Employee employee = mapper.map(employeeData,Employee.class);
        Optional<Address> address = this.addressRepository.findByCountryAndCity
                            (employeeData.getAddressDTO().getCountry(),
                            employeeData.getAddressDTO().getCity());
        if (address.isPresent()){
            employee.setAddress(address.get());
        }

        return  this.employeeRepository.save(employee);

    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<EmployeeDTO> findAllDTO() {
           return this.employeeRepository.findAll()
                   .stream()
                   .map(e ->mapper.map(e, EmployeeDTO.class))
                   .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findByIdDTO(long id) {
        return this.employeeRepository.findNamesById(id);
    }

    @Override
    public EmployeeNameSalaryDTO findByIdDTONameSalary(long id) {
        return this.employeeRepository.findFirstNameSalaryById(id);
    }
}
