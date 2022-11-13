package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose extends BaseEntity{
    @Column(nullable = false )
    private String name;

    @Column(length = 1000)
    private String comment;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;

    public Diagnose() {
        super();
        this.patients = new HashSet<>();
    }

    public Diagnose(String name) {
        this();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
    public void addPatient( Patient  patients) {
        this.patients.add(  patients);
    }
}
