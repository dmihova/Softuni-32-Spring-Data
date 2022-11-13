package entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {
    @Column
    private int numberOfPassengers;

    public PassengerVehicle() {
        super();
    }

    public PassengerVehicle(String type,int numberOfPassengers) {
        super(type);
        this.numberOfPassengers = numberOfPassengers;
    }
}
