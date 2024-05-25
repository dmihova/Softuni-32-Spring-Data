package com.softuni.services;


import com.softuni.models.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate dateBefore);

    List<Author> findAll();

    Author findByFirstNameAndLastName(String george, String powell);
}
