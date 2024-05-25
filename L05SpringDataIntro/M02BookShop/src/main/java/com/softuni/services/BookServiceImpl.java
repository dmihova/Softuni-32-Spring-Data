package com.softuni.services;

import com.softuni.models.entities.Author;
import com.softuni.models.entities.Book;
import com.softuni.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByReleaseDateAfter(LocalDate dateAfter) {
        return bookRepository.findByReleaseDateAfter(dateAfter);
    }

    @Override
    public List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author) {
        return bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);
    }
}
