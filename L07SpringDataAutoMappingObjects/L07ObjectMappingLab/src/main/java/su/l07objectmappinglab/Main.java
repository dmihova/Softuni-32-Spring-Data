package su.l07objectmappinglab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.l07objectmappinglab.model.dto.CreateAddressDTO;
import su.l07objectmappinglab.model.dto.CreateEmployeeDTO;
import su.l07objectmappinglab.model.dto.EmployeeDTO;
import su.l07objectmappinglab.model.dto.EmployeeNameSalaryDTO;
import su.l07objectmappinglab.model.entity.Address;
import su.l07objectmappinglab.model.entity.Employee;
import su.l07objectmappinglab.service.AddressService;
import su.l07objectmappinglab.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class Main implements CommandLineRunner {

    private AddressService addressService;
    private EmployeeService employeeService;

    @Autowired
    public Main(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

       // Address address =createAddress();
       // Employee employee =createEmployee();
        //printAllEmployees();
       // printAllEmployeesDTO();
      //  printEmployeeName();
        printEmployeeNameAndSalary();



    }

    private void printEmployeeNameAndSalary() {
        long id =1;
        EmployeeNameSalaryDTO employeeNameSalaryDTO = this.employeeService.findByIdDTONameSalary(id);
        System.out.println(employeeNameSalaryDTO.getInfo());
    }

    private void printEmployeeName() {
        long id =1;
        EmployeeDTO employeeDTO = this.employeeService.findByIdDTO(id);
        System.out.println(employeeDTO);
    }

    private void printAllEmployeesDTO() {
        List<EmployeeDTO> employees =employeeService.findAllDTO();
        for (EmployeeDTO employee:employees ){
            System.out.println(employees);
        }
    }

    private void printAllEmployees() {
        List<Employee> employees =employeeService.findAll();
        for (Employee employee:employees ){
            System.out.println(employees);
        }
    }

    private Employee createEmployee() {
        String firstName ="Fname2";
        String lastName= "LName2";
        BigDecimal salary = BigDecimal.valueOf(6000.0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birthdate = LocalDate.parse("11/10/1999", formatter);

        String country ="Bulgaria";
        String city= "Ruse";
        CreateAddressDTO addresData = new CreateAddressDTO(country,city);

        CreateEmployeeDTO employeeData  = new CreateEmployeeDTO(firstName,lastName,salary,birthdate,addresData);

        return this.employeeService.create(employeeData);
    }

    private Address createAddress() {
        String country ="Bulgaria";
        String city= "Varna";
        CreateAddressDTO addresData = new CreateAddressDTO(country,city);
        return addressService.create(addresData);
    }
}
