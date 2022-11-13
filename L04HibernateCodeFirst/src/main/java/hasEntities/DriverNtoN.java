package hasEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="drivers_nton")
public class DriverNtoN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name ="cars_drivers",
            joinColumns = @JoinColumn(name="driver_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="car_id",referencedColumnName = "id")
    )
    private List<CarNtoN> cars;



    public DriverNtoN() {
        this.cars=new ArrayList<>();
    }

    public DriverNtoN(String name) {
        this();
        this.name = name;
    }
    public void addCar(CarNtoN car){
        cars.add(car);
    }
}
