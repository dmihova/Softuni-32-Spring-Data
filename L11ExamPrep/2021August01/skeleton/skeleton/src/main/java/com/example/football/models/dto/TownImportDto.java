package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDto {

    @Size(min = 2)
    private String name;

    @Positive
    private int population;

    @Size(min = 10)
    private String travelGuide;

    public TownImportDto() {
    }

    public TownImportDto(String name, int population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
