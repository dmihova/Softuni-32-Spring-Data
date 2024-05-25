package com.softuni.services;

import com.softuni.models.entities.Author;
import com.softuni.models.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    public List<Book> findByReleaseDateAfter(LocalDate dateAfter);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
