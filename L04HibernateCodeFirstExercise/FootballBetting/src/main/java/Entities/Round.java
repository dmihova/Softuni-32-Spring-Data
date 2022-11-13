package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="rounds")
public class Round extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    public Round() {
        super();
    }

    public Round(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
