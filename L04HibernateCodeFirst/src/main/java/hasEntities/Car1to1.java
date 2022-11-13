package hasEntities;

import jakarta.persistence.*;

@Entity
@Table (name="cars_1to1")
public class Car1to1 {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private long id;

       @Column
       private String name;

       @OneToOne
       @JoinColumn(name="plate_number_id" ,referencedColumnName = "id")
       private PlateNumber1to1 plateNumber;




       public Car1to1() {
       }

       public Car1to1(String name, PlateNumber1to1 plateNumber) {
              this.name = name;
              this.plateNumber = plateNumber;
       }
}
