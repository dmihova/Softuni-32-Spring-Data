package com.softuni.models.entities;

import com.softuni.models.enums.AgeRestriction;
import com.softuni.models.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    @Column(length = 50,nullable = false)
    private String title;

    @Column(length = 1000 )
    private String description;

   //@Enumerated(EnumType.STRING) - to keep as string not int
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;


    @Column
    private BigDecimal price;

    @Column (nullable = false)
    private int copies;

    @Column(name = "release_date",nullable = true)
    private LocalDate releaseDate;

     @Column(name = "age_restriction" ,nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    // JoinTable not needed as direction is one way and no need to  map the other relation
  /*   @JoinTable(
          name = "books_categories",
          joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
     )*/
    private Set<Category> categories;



    public Book() {
    }

    public Book(String title, EditionType editionType, BigDecimal price, int copies,
                LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories) {
        this();
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
