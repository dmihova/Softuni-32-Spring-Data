package org.softuni.service;



import org.softuni.model.dto.CreateEmployeeDTO;
import org.softuni.model.dto.EmployeeDTO;
import org.softuni.model.dto.EmployeeNameSalaryDTO;
import org.softuni.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeData);

    List<Employee> findAll();

    List<EmployeeDTO> findAllDTO();

    EmployeeDTO findByIdDTO(long id);

    EmployeeNameSalaryDTO findByIdDTONameSalary(long id);
}
