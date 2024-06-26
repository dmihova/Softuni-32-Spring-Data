package com.softuni.models.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="authors")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author", targetEntity = Book.class, fetch = FetchType.EAGER)
    private Set<Book> books;


    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String lastName) {
        this();
        lastName = lastName;
    }

    public Author(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Book> getBooks() {
         return Collections.unmodifiableSet(books) ;
    }

   public void addBook ( Book  book ) {
    this.books.add(book) ;
}
}