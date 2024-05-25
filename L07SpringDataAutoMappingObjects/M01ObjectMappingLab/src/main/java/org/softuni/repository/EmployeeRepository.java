package org.softuni.repository;

import org.softuni.model.dto.EmployeeDTO;
import org.softuni.model.dto.EmployeeNameSalaryDTO;
import org.softuni.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query ("SELECT new org.softuni.model.dto.EmployeeDTO(e.firstName, e.lastName)"+
        " FROM Employee AS e WHERE e.id= :id")
    EmployeeDTO findNamesById(long id);

    EmployeeNameSalaryDTO findFirstNameSalaryById(long id);
}
