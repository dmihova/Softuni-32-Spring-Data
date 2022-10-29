package Entities;


import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

import java.time.LocalDate;

@Entity(name="users")
public class User {
    @Id
    private long id;

    @Column(name="user_name")
    private String username;

    @Column(name="age")
    private int age;

    @Column(name="registration_date")
    private LocalDate registration;

    public User() {
    }

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
