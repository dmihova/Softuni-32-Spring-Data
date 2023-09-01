package su.productsshop.entiies.product;

import jakarta.persistence.*;
import su.productsshop.entiies.category.Category;
import su.productsshop.entiies.user.User;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(  nullable = false)
    private String name;
    // min 3

    @Column
    private BigDecimal price;

    @ManyToMany private Set<Category> categories;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    public Product() {
        this.categories =new HashSet<>();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyerId) {
        this.buyer = buyerId;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User sellerId) {
        this.seller = sellerId;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
