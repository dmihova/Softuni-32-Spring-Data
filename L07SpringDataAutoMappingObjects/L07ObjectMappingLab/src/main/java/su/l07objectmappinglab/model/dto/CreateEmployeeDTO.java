package su.l07objectmappinglab.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEmployeeDTO {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthdate;
    private CreateAddressDTO addressDTO;


    public CreateEmployeeDTO(String firstName, String lastName, BigDecimal salary,LocalDate birthdate, CreateAddressDTO addressDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.addressDTO = addressDTO;
        this.birthdate=birthdate;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public CreateAddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(CreateAddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
}
