package org.softuni;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.softuni.model.dto.EmployeeDTO;
import org.softuni.model.entity.Address;
import org.softuni.model.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper modelMapper =new ModelMapper();
        Address address =new Address("Bulgaria","Sofia");
        BigDecimal salary = BigDecimal.valueOf(5000.0);
        Employee employee =new Employee("FName","LName",salary,address);
        PropertyMap<Employee,EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
            }
        };
        modelMapper.addMappings(propertyMap);
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        System.out.println(employeeDTO);



        ModelMapper modelMapper2 =new ModelMapper();
        TypeMap<Employee, EmployeeDTO> typeMap2 =modelMapper2.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap2.addMappings(mapping->mapping.map(
                    source ->source.getAddress().getCity(),
                   EmployeeDTO::setCity )
        );
        typeMap2.validate();
        EmployeeDTO employeeDTO1 = typeMap2.map(employee );
        System.out.println(employeeDTO1);

    }
}
