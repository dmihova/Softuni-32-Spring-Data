package org.softuni.services;

import org.softuni.entities.Author;
import org.softuni.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        long authorId = new Random().nextLong(  size) + 1;
        return this.authorRepository.findById(  authorId).get();
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

    @Override
    public List<Author> findAllByFirstNameEndingWith(String end) {
        return authorRepository.findAllByFirstNameEndingWith(end);
    }

    @Override
    public List<String> getAuthorWithCopies() {
        return authorRepository
                .findAllWithTotalCopies()
                .stream()
                .map(a -> a.getInfo())
                .collect(Collectors.toList());
    }
}
