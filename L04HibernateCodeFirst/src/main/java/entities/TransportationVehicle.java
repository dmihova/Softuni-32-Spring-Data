package entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class  TransportationVehicle extends Vehicle{
    @Column
    private double loadCapacity;

    public TransportationVehicle() {
        super();
    }

    public TransportationVehicle(String type,double loadCapacity) {
        super(type);
        this.loadCapacity = loadCapacity;
    }
}

