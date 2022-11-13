package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

    @Column
    private LocalDate date;

    @Column(length = 1000)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @ManyToMany
    @JoinTable(
            name = "visitations_medicaments",
            joinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    )
    private final Set<Medicament> medicaments;


    public Visitation() {
        super();
        this.date = LocalDate.now();
        this.medicaments = new HashSet<>();
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void addMedicament(Medicament medicament) {
        this.medicaments.add(medicament);
    }

    @Override
    public String toString() {
        return "Visitation " + getId() + "/" + date + System.lineSeparator() +
                "Patient: " + patient.getFirstName() + " " + patient.getLastName() + System.lineSeparator() +
                "Diagnose: " + diagnose.getName() + System.lineSeparator() +
                "Medicaments: " + medicaments.stream().map(Medicament::getName).collect(Collectors.joining(", "));
    }
}