package org.softuni.services;



import org.softuni.entities.Author;
import org.softuni.entities.Book;
import org.softuni.entities.dto.BookTitleTypeRestrictionPrice;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    public List<Book> findByReleaseDateAfter(LocalDate dateAfter);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    List<Book> findAllByAgeRestriction(AgeRestriction adult);

    List<String> findAllTitlesByAgeRestriction(AgeRestriction adult);

    List<String> findAllTitlesByEditionAndCopies(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);

    List<String> findAllTitlesNotReleasedInYear(int year);

    List<String> findByReleaseDateLessThanAndReleaseDateGreaterThan(LocalDate dateStart, LocalDate dateEnd);

    List<Book> findByReleaseDateLessThan(LocalDate date);

    List<Book>findAllByTitleContaining(String contains);

    List<Book>findAllByAuthorLastNameStartingWith(String contains);

    int countWithTitleLongerThan(int size);

    List<BookTitleTypeRestrictionPrice> getReducedBooksByTitle(String title);

    int addCopiesReleaseAfter(int copies, LocalDate date);

    int deleteByCopiesLessThan(int copies);

    int getBooksCountFromStoredProcedureByAuthorName(String firstName, String lastName);
}
