package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name= "bikes")
public class Bike extends PassengerVehicle{
    private static final String VEHICLE_TYPE= "bike";

    public Bike() {
        super(VEHICLE_TYPE,  1);
    }
}
