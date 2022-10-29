package Entities;


import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

import java.time.LocalDate;

@Entity(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;


    @Column(name = "age")
    private int age;

    @Column(name = "createdOn")
    private LocalDate createdOn;

    @Column(name = "nickname")
    private String nickname;


    public Account() {
    }

    public Account(String name, Integer age, LocalDate createdOn, String nickname) {
        this.name = name;
        this.age = age;
        this.createdOn = createdOn;
        this.nickname = nickname;
    }

    public Account(String name, LocalDate createdOn, Integer age) {
        this.name = name;
        this.createdOn = createdOn;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
