package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="competitions")
public class Competition extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type")
    private CompetitionType type;

    public Competition() {
        super();
    }

    public Competition(String name, CompetitionType type) {
        this();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }
}
