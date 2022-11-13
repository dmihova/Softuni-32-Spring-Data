package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "trucks")
public class Truck extends TransportationVehicle {
    private static final String VEHICLE_TYPE= "Truck";

    public Truck(double loadCapacity) {
         super(VEHICLE_TYPE ,loadCapacity);
    }
}
