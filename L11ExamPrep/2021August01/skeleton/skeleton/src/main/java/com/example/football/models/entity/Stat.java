package com.example.football.models.entity;

import com.example.football.util.Messages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity{

    @Column
    private float shooting;

    @Column
    private float passing;

    @Column
    private float endurance;

    public Stat() {
    }

    public Stat(float shooting, float passing, float endurance) {
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    @Override
    public String toString() {
        return shooting + Messages.DASH + passing + Messages.DASH + endurance;
    }
}
