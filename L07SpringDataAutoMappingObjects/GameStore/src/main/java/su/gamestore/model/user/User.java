package su.gamestore.model.user;

import su.gamestore.model.BaseEntity;
import su.gamestore.model.order.Order;
import su.gamestore.model.game.Game;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name ="users")
public class User extends BaseEntity {
    private static final String INVALID_EMAIL_MESSAGE = "Invalid e-mail%n" +
            "Please register email in format <user>@<host>, where:%n" +
            "<user> is a sequence of letters and digits, where '.', '-' and '_' can appear between them.%n" +
            "<host> is a sequence of at least two words, separated by dots '.'.%n" +
            "Each word is sequence of letters and can have hyphens '-' between the letters.";
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9]+([-_.]?[A-Za-z0-9]+)*@([A-Za-z]+(-[A-Za-z]+)*)([.]([A-Za-z]+(-[A-Za-z]+)*))+$";

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL_MESSAGE)
    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private boolean admin;

    @ManyToMany
    private Set<Game> games;

    @OneToMany (targetEntity = Order.class,mappedBy = "buyer")
    private Set<Order> orders;

    public User() {
        super();
        this.orders=new LinkedHashSet<>();
        this.games= new LinkedHashSet<>();
    }

    public User(String fullName, String password, String email) {
        this();
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

