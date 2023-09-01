package su.l07objectmappinglab.service;

import su.l07objectmappinglab.model.dto.CreateEmployeeDTO;
import su.l07objectmappinglab.model.dto.EmployeeDTO;
import su.l07objectmappinglab.model.dto.EmployeeNameSalaryDTO;
import su.l07objectmappinglab.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeData);

    List<Employee> findAll();

    List<EmployeeDTO> findAllDTO();

    EmployeeDTO findByIdDTO(long id);

    EmployeeNameSalaryDTO findByIdDTONameSalary(long id);
}
