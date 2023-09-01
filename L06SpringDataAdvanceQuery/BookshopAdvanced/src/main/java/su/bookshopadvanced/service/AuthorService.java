package su.bookshopadvanced.service;


import su.bookshopadvanced.model.entity.Author;
import su.bookshopadvanced.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> findAllByFirstNameEndingWith(String end);

    List<AuthorNamesWithTotalCopies>getAuthorWithCopies();
}
