package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="competition_type")
public class CompetitionType extends BaseEntity{
    @Column(name = "type", nullable = false)
    private String competition_type;

    public CompetitionType() {
        super();
    }

    public CompetitionType(String competition_type) {
        this();
        this.competition_type = competition_type;
    }

    public String getCompetition_type() {
        return competition_type;
    }

    public void setCompetition_type(String competition_type) {
        this.competition_type = competition_type;
    }
}
