package hasEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "planes_1ton")
public class Plane1toN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column
    private String model;

    @ManyToOne
    @JoinColumn(name ="company_id",referencedColumnName = "id")
    private Company1toN company;

    public Plane1toN() {

    }
    public Plane1toN(String model ) {
        this();
        this.model = model;
    }
    public Plane1toN(String model, Company1toN company) {
        this();
        this.model = model;
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Company1toN getCompany() {
        return company;
    }

    public void setCompany(Company1toN company) {
        this.company = company;
    }
}
