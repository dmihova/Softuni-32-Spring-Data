package org.softuni.model.dto;

import java.math.BigDecimal;

public interface EmployeeNameSalaryDTO {

     String getFirstName()  ;
     BigDecimal getSalary() ;


    default String   getInfo(){
        return "EmployeeNameSalaryDTO{" +
                "firstName='" + getFirstName() + '\'' +
                ", salary=" + getSalary() +
                '}';
    }
}
