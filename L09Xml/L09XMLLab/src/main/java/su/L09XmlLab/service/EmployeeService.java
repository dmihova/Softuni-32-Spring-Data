package su.L09XmlLab.service;


import su.L09XmlLab.model.dto.CreateEmployeeDTO;
import su.L09XmlLab.model.dto.EmployeeDTO;
import su.L09XmlLab.model.dto.EmployeeNameSalaryDTO;
import su.L09XmlLab.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeData);

    List<Employee> findAll();

    List<EmployeeDTO> findAllDTO();

    EmployeeDTO findByIdDTO(long id);

    EmployeeNameSalaryDTO findByIdDTONameSalary(long id);
}
