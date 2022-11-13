package Entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name ="countries")
public class Country {
    @Id
    @Column(length = 3,unique=true)
    private String id;

    @Column(name = "name")
    private String name;


    @ManyToMany
    @JoinTable(
            name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id")
    )
    private Set<Continent> continents;

    public Country() {
        this.continents = new LinkedHashSet<>();
    }

    public Country(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinents() {
        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
    public void addContinent(Continent continent){
        this.continents.add(continent);
    }
}
