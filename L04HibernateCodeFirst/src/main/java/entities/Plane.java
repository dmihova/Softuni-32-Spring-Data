package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="planes")
public class Plane extends Vehicle {
    private static final String VEHICLE_TYPE= "plane";
    private  int passengerCapacity;

    public Plane( ) {
        super( VEHICLE_TYPE);
    }

    public Plane(String model,String fuelType,int passengerCapacity) {
        this( );
        this.model=model;
        this.fuelType=fuelType;
        this.passengerCapacity = passengerCapacity;
    }
}
