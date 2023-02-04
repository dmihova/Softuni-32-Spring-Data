package com.example.football.models.dto;

import com.example.football.models.entity.PlayerPosition;
import com.example.football.util.LocalDateAdapter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDto {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 3)
    private String lastName;

    @Email
    @NotNull
    @XmlElement
    private String email;

    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "birth-date")
    private LocalDate birthDate;

    @NotNull
    @XmlElement
    private PlayerPosition position;

    @XmlElement
    @NotNull
    private NameDto town;

    @XmlElement
    @NotNull
    private NameDto team;

    @XmlElement
    @NotNull
    private IdDto stat;

    public PlayerImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public NameDto getTown() {
        return town;
    }

    public void setTown(NameDto town) {
        this.town = town;
    }

    public NameDto getTeam() {
        return team;
    }

    public void setTeam(NameDto team) {
        this.team = team;
    }

    public IdDto getStat() {
        return stat;
    }

    public void setStat(IdDto stat) {
        this.stat = stat;
    }
}