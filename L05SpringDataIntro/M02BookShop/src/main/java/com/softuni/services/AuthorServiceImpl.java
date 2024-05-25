package com.softuni.services;

import com.softuni.models.entities.Author;
import com.softuni.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = authorRepository.count();
        int authorId = new Random().nextInt((int) size) + 1;
        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public List<Author> findDistinctByBooksReleaseDateBefore(LocalDate dateBefore) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(dateBefore);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        return this.authorRepository.findByFirstNameAndLastName(firstName,lastName);
    }
}
