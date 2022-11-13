package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
public class Car extends PassengerVehicle{
    private static final String VEHICLE_TYPE= "car";


    public Car(int seats ) {
        super( VEHICLE_TYPE,seats);
    }

    public Car(String model,String fuelType,int seats) {
        this( seats);
        this.model=model;
        this.fuelType=fuelType;
    }
}
