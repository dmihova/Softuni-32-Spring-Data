package su.L09XmlLab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.L09XmlLab.model.dto.EmployeeDTO;
import su.L09XmlLab.model.dto.EmployeeNameSalaryDTO;
import su.L09XmlLab.model.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query ("SELECT new su.L09XmlLab.model.dto.EmployeeDTO(e.firstName, e.lastName)"+
        " FROM Employee AS e WHERE e.id= :id"
   )
    EmployeeDTO findNamesById(long id);

    EmployeeNameSalaryDTO findFirstNameSalaryById(long id);
}
