package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column()
    private String name;

    @Column()
    private double quantity;

    @Column()
    private BigDecimal price;

    @OneToMany(mappedBy = "product", targetEntity = Sale.class,
            fetch = FetchType.LAZY // ,  cascade =CascadeType.ALL
    )
    private Set<Sale> sales;


    public Product() {
        this.sales =new LinkedHashSet<>();
    }
    public Product(String name, double quantity, BigDecimal price) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
