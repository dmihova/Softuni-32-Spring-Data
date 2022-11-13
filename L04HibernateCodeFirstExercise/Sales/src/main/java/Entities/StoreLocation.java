package Entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name="location_name")
    private String locationName;

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class,
            fetch = FetchType.LAZY // ,  cascade =CascadeType.ALL
    )
    private Set<Sale> sales;

    public StoreLocation() {
        this.sales =new LinkedHashSet<>();
    }

    public StoreLocation(String locationName) {
        this();
        this.locationName = locationName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
