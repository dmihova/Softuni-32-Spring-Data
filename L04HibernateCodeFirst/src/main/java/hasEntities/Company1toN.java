package hasEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="companies_1ton")
public class Company1toN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "company", targetEntity = Plane1toN.class,
        fetch = FetchType.LAZY ,
        cascade =CascadeType.ALL
    )
    private List<Plane1toN> planes;

    public Company1toN() {
        this.planes =new ArrayList<>();
    }

    public Company1toN(String name) {
        this();
        this.name = name;
    }
    public void addPlane(Plane1toN newPlane){
        this.planes.add(newPlane);
    }
}
