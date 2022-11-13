package Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "storeLocation_id", referencedColumnName = "id")
    private StoreLocation storeLocation;


    @Column(name = "date")
    private Date date;

    public Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation, Date date) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }
}
