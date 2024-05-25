package org.softuni.repositories;

import org.softuni.entities.Author;
import org.softuni.entities.Book;
import org.softuni.entities.dto.BookTitleTypeRestrictionPrice;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

    List<Book> findByReleaseDateAfter(LocalDate dateAfter);

    int countByReleaseDateAfter(LocalDate dateBefore);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    @Query("SELECT b.title FROM Book b WHERE b.ageRestriction= :ageRestriction")
    List<String> findAllTitlesByAgeRestriction(AgeRestriction ageRestriction);

    @Query("SELECT b.title FROM Book b WHERE b.editionType= :editionType AND copies<:copies")
    List<String> findAllTitlesByEditionAndCopies(EditionType editionType, int copies);

    @Query("select b.title from Book b where b.releaseDate < :beforeDate OR b.releaseDate > :afterDate")
    List<String> findByReleaseDateLessThanAndReleaseDateGreaterThan(LocalDate beforeDate, LocalDate afterDate);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);

   @Query("select b.title from Book b where YEAR(b.releaseDate)<>:year")
   List<String> findAllTitlesNotReleasedInYear(int year);

    @Query("select b from Book b where b.releaseDate < :releaseDate")
    List<Book> findAllByReleaseDateLessThan(LocalDate releaseDate);


    List<Book> findAllByTitleContaining(String contains);


    List<Book> findAllByAuthorLastNameStartingWith(String start);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE length(b.title)>:size")
    int countWithTitleLongerThan(int size);

    @Query("SELECT b.title as title, b.editionType as editionType, "+
            " b.ageRestriction as ageRestriction, b.price as price "+
            " FROM Book AS b WHERE  b.title = :title")
    List<BookTitleTypeRestrictionPrice> findAllByTitleReduced(String  title);
    @Query("Update Book b set b.copies = b.copies + :copies WHERE b.releaseDate >:date ")
    @Modifying
    int addCopiesReleaseAfter(int copies, LocalDate date);

    int deleteByCopiesLessThan(int copies);
    @Procedure("udp_books_written_by")
    int countBooksWrittenByAuthorStoredProcedure(String firstName, String lastName);

}
