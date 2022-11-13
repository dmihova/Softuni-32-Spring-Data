package Entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column()
    private String name;

    @Column()
    private String  email;

    @Column()
    private String creditCardNumber;


    @OneToMany(mappedBy = "customer", targetEntity = Sale.class,
            fetch = FetchType.LAZY // ,  cascade =CascadeType.ALL
    )
    private Set<Sale> sales;

    public Customer() {
        this.sales =new LinkedHashSet<>();
    }

    public Customer(String name, String email, String creditCardNumber) {
        this();
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }
}