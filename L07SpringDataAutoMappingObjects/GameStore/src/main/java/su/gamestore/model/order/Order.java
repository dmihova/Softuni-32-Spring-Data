package su.gamestore.model.order;

import su.gamestore.model.BaseEntity;
import su.gamestore.model.game.Game;
import su.gamestore.model.user.User;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table (name ="orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User buyer;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    public Order() {
        super();
        this.products =new LinkedHashSet<>();
    }

    public Order(User buyer) {
        this();
        this.buyer = buyer;
    }


    public Order(User buyer, Set<Game> gameToBuy) {
        this();
        this.products = gameToBuy;
        this.buyer = buyer;
    }
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }

}
