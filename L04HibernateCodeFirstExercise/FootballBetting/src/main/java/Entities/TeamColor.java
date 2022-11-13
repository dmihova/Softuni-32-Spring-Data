package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="colors")
public class TeamColor extends BaseEntity {

    @Column(name = "name" , nullable = false)
    private String name;

    public TeamColor() {
        super();
    }

    public TeamColor(String name) {
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
