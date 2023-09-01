package su.L09XmlLab.model.dto;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CompanyDTO  {
    @Expose
    private String name;
    @Expose
    private List<CreateEmployeeDTO> employees;

    public CompanyDTO() {
        this.employees = new ArrayList<>();
    }

    public CompanyDTO(String name, List<CreateEmployeeDTO> employees) {
        this.name = name;
        this.employees = employees;
    }

    public CompanyDTO(String name) {
        this();
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
