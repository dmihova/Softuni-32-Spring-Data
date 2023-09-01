package su.l07objectmappinglab;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.l07objectmappinglab.model.dto.EmployeeDTO;
import su.l07objectmappinglab.model.entity.Address;
import su.l07objectmappinglab.model.entity.Employee;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper modelMapper =new ModelMapper();

        Address address =new Address("Bulgaria","Sofia");
        BigDecimal salary = BigDecimal.valueOf(5000.0);
        Employee employee =new Employee("FName","LName",salary,address);


/*
        PropertyMap<Employee,EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
            }
        };
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        System.out.println(employeeDTO);
*/



        TypeMap<Employee,EmployeeDTO> typeMap =modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping->mapping.map(
                    source ->source.getAddress().getCity(),
                   EmployeeDTO::setCity )
        );
        typeMap.validate();



        EmployeeDTO employeeDTO1 = typeMap.map(employee );
        System.out.println(employeeDTO1);

    }
}
