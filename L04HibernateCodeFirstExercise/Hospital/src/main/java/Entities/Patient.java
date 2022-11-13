package Entities;

import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "patients")
public class Patient extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private String address;

    @Column
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private Blob picture;

    @Column(name = "has_medical_insurance", nullable = false)
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(
            name = "patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    )
    private Set<Diagnose> diagnoses;


    @ManyToMany
    @JoinTable(
            name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    )
    private Set<Medicament> medicaments;

    public Patient() {
        super();
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medicaments = new HashSet<>();

    }

    public Patient(String firstName, String lastName, LocalDate birthDate, boolean hasMedicalInsurance) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hasMedicalInsurance = hasMedicalInsurance;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + System.lineSeparator() +
                "address: " + (address == null ? "N/A" : address) + System.lineSeparator() +
                "e-mail: " + (email == null ? "N/A" : email) + System.lineSeparator() +
                "birth date: " + birthDate + System.lineSeparator() +
                "insurance: " + (hasMedicalInsurance ? "yes" : "no") + System.lineSeparator() +
                "visitation number: " + visitations.size();
    }
}