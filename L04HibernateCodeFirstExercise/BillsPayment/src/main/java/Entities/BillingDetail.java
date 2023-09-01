package Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "billing_details")

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail extends BaseEntity {
     @Column(nullable = false)
    private String number;

    @ManyToMany(mappedBy = "billingDetails")
    private Set<Payer> payers;

    public BillingDetail() {
        super();
        this.payers = new HashSet<>();
    }

    public BillingDetail(String number, Set<Payer> payers) {
        this();
        this.number = number;
        this.payers = payers;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Payer> getPayers() {
        return payers;
    }

    public void setPayers(Set<Payer> payers) {
        this.payers = payers;
    }
}