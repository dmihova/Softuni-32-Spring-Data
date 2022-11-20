package su.bookshop.models;

import javax.persistence.*;

@Entity
@Table(name ="categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this( );
        this.name = name;
    }
}
