package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="towns")
public class Town extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Town() {
        super();
    }

    public Town(String name, Country country) {
        this();
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
