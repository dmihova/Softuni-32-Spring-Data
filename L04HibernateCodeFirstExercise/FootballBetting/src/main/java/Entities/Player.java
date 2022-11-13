package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "squad_number",nullable = false)
    private int squadNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_currently_injured")
    private boolean isCurrentlyInjured;


    public Player() {
        super();
    }

    public Player(String name, int squadNumber, boolean isCurrentlyInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.isCurrentlyInjured = isCurrentlyInjured;
    }

    public Player(String name, int squadNumber, Team team, Position position, boolean isCurrentlyInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.team = team;
        this.position = position;
        this.isCurrentlyInjured = isCurrentlyInjured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }
}
