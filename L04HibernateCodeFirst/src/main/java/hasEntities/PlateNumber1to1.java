package hasEntities;

import jakarta.persistence.*;

@Entity
@Table(name="plate_numbers_1to1")
public class PlateNumber1to1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String number;

    @OneToOne(targetEntity = Car1to1.class, mappedBy = "plateNumber")
    private Car1to1 car1to1;

    public PlateNumber1to1() {
    }

    public PlateNumber1to1(String number) {
        this.number = number;
    }
}
