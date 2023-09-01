package su.L09XmlLab;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.L09XmlLab.model.dto.CreateEmployeeDTO;
import su.L09XmlLab.model.dto.EmployeeDTO;
import su.L09XmlLab.model.dto.EmployeeNameSalaryDTO;
import su.L09XmlLab.model.dto.addresses.AddressDTO;
import su.L09XmlLab.model.dto.addresses.CreateAddressDTO;
import su.L09XmlLab.model.entity.Address;
import su.L09XmlLab.model.entity.Employee;
import su.L09XmlLab.service.AddressService;
import su.L09XmlLab.service.EmployeeService;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//@Component
public class MainJsonToDb implements CommandLineRunner {
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private Scanner scanner;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapper mapper;

    public MainJsonToDb(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson, ModelMapper mapper) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        createAddress();
    }

    private Address createAddress() {
        String input = this.scanner.nextLine();
        CreateAddressDTO addressData = gson.fromJson(input,CreateAddressDTO.class);
        Address address= addressService.create(addressData);
        AddressDTO addressDTO  =this.mapper.map(address,AddressDTO.class);
        System.out.println(gson.toJson(addressDTO));
        return address;
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


}
