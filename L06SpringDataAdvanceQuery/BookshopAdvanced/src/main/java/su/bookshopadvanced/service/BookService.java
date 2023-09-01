package su.bookshopadvanced.service;


import su.bookshopadvanced.model.entity.AgeRestriction;
import su.bookshopadvanced.model.entity.Book;
import su.bookshopadvanced.model.entity.BookTitleTypeRestrictionPrice;
import su.bookshopadvanced.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction minor);

    List<String> findAllTitlesByAgeRestriction(AgeRestriction minor);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType,int copies);

    List<String> findAllTitlesByAgeRestrictionOptmzQuery(AgeRestriction minor);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);

    List<String> findAllTitlesNotReleasedInYear(int year);



    List<String>  findAllTitlesReleasedBefore(LocalDate date);

    List<String> findAllTitlesContaining(String contains);

    List<String>  findAllByAuthorLastNameStartingWith(String contains);

    int countWithTitleLongerThan(int size);

    List<BookTitleTypeRestrictionPrice> getReducedBooksByTitle(String title);

    int addCopiesRealeaseAfter(int copies, LocalDate date);

    int deleteByCopiesLessThan(int copies);

    int getBooksCountFromStoredProcedureByAuthorName(String firstName,String lastName);
}
