package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="positions")
public class Position  {
    @Id
    @Column(length = 2,unique=true)//letters – GK, DF, MF, FW…),
    private String id;

    @Column
    private String description;

    public Position() {
    }

    public Position(String id,String description) {
        this.id=id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
