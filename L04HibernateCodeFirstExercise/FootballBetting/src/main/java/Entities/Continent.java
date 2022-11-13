package Entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name ="continents")
public class Continent extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;


    @ManyToMany
    @JoinTable(
            name = "countries_continents",
            joinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id")
    )
    private Set<Country> countries;

    public Continent() {
        super();
        this.countries  =  new LinkedHashSet<>();
    }

    public Continent(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
    public void addCountry(Country country){
        this.countries.add(country);
    }
}
