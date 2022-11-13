package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "medicaments")
public class Medicament extends BaseEntity{


    @Column(nullable = false )
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Visitation> visitations;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Patient> patients;


    public Medicament() {
        super();
        this.patients = new HashSet<>();
        this.visitations = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }
}