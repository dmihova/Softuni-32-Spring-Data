package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity

@Table(name="vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )// A single table per class hierarchy.
//@DiscriminatorColumn(name = "type_id")  +DiscriminatorValue in subclass

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //separate table for each persistent class
//@Inheritance(strategy = InheritanceType.JOINED)//A strategy in which fields that are specific to a subclass are mapped
// to a separate table than the fields that are common to the parent class, and a join is performed to instantiate the subclass.
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE )   //  the persistence provider must assign primary keys for the entity using an underlying database table to ensure uniqueness.
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)  //   the persistence provider must assign primary keys for the entity using a database sequence.
    // @GeneratedValue(strategy = GenerationType.IDENTITY)  //  the persistence provider must assign primary keys for the entity using a database identity colum
    // @GeneratedValue(strategy = GenerationType.UUID /String RFC 4122 Universally Unique IDentifier.
    // @GeneratedValue(strategy = GenerationType.AUTO  - auto select strategy

    private long id;

    @Basic
    protected String type;

    @Column
    protected String model;

    @Column
    protected BigDecimal price;

    @Column(name = "fuel_type")
    protected String fuelType;

    protected Vehicle() {

    }

    protected Vehicle(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
