package com.softuni.M01SDIntroLab.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private int age;

    @OneToMany( targetEntity = Account.class, mappedBy = "user",cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
         this.accounts= new ArrayList<>();
    }

    public User(String username, int age) {
        this();
        this.username = username;
        this.age = age;
    }

    public User(String username, int age,  Account  accounts) {
        this();
        this.username = username;
        this.age = age;
        this.accounts.add(accounts);
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

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(this.accounts);
    }
    public List<Long> getAccountIds() {
        return this.accounts
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }
}
