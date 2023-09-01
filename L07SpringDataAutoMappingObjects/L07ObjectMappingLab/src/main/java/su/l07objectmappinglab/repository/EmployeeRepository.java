package su.l07objectmappinglab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.l07objectmappinglab.model.dto.EmployeeDTO;
import su.l07objectmappinglab.model.dto.EmployeeNameSalaryDTO;
import su.l07objectmappinglab.model.entity.Address;
import su.l07objectmappinglab.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query ("SELECT new su.l07objectmappinglab.model.dto.EmployeeDTO(e.firstName, e.lastName)"+
        " FROM Employee AS e WHERE e.id= :id"
   )
    EmployeeDTO findNamesById(long id);

    EmployeeNameSalaryDTO findFirstNameSalaryById(long id);
}
