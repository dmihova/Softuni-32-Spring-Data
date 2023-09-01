package su.bookshopadvanced.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import su.bookshopadvanced.model.entity.AgeRestriction;
import su.bookshopadvanced.model.entity.Book;
import su.bookshopadvanced.model.entity.BookTitleTypeRestrictionPrice;
import su.bookshopadvanced.model.entity.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    @Query("SELECT b.title FROM Book b WHERE b.ageRestriction= :ageRestriction")
    List<String> findAllTitlesByAgeRestrictionOptmzQuery(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);


    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate startDate, LocalDate endDate);

    List<Book>  findAllByTitleContaining(String contains);

    List<Book>  findAllByAuthorLastNameStartingWith(String lastName);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE length(b.title)>:size")
    int countByTitleLongerThan(int size);
    @Query("SELECT b.title AS title, b.editionType AS editionType, "+
            " b.ageRestriction AS ageRestriction, b.price as price "+
            "FROM Book b " +
            "WHERE b.title = :title "  )
    List<BookTitleTypeRestrictionPrice> getReducedBooksByTitle(String title);

    @Query("Update Book b set b.copies = b.copies + :copies WHERE b.releaseDate >:date ")
    @Modifying
    int addCopiesRealeaseAfter(int copies, LocalDate date);

    @Modifying
    int deleteByCopiesLessThan(int copies);

    @Procedure("udp_books_written_by")
    int countBooksWrittenByAuthorStoredProcedure(String firstName, String lastName);
}