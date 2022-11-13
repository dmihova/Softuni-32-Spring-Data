package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name ="teams")
public class Team extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "initials" ,length = 3)
    private String initials;

    @Column(name = "budget"  )
    private BigDecimal budget;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color")
    private TeamColor primaryColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color")
    private TeamColor secondaryColor;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    public Team() {
        super();
    }

    public Team(String name, String logo, String initials, BigDecimal budget, TeamColor primaryColor, TeamColor secondaryColor, Town town) {
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.budget = budget;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public TeamColor getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(TeamColor primaryColor) {
        this.primaryColor = primaryColor;
    }

    public TeamColor getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(TeamColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
