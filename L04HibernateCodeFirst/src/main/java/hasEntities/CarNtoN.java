package hasEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cars_nton")
public class CarNtoN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

   @ManyToMany (fetch = FetchType.LAZY  )
   @JoinTable(name ="cars_drivers",
            joinColumns = @JoinColumn(name="car_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="driver_id",referencedColumnName = "id")
   )
    private List<DriverNtoN> drivers;




    public CarNtoN() {
        this.drivers = new ArrayList<>();
    }

    public CarNtoN(String name) {
        this();
        this.name = name;
    }

    public void addDriver(DriverNtoN driver){
        this.drivers.add(driver);
    }
}

