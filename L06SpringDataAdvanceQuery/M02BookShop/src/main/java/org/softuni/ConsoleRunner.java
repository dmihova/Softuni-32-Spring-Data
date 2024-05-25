package org.softuni;


import org.softuni.entities.Author;
import org.softuni.entities.Book;
import org.softuni.entities.dto.AuthorNamesWithTotalCopies;
import org.softuni.entities.dto.BookTitleTypeRestrictionPrice;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;
import org.softuni.services.AuthorService;
import org.softuni.services.BookService;
import org.softuni.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;


    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // t00_initData();
        //t01BooksByAgeRestriction();
        //t02BooksByEditionAndCopiesLess();
        //t03PrintBookByPriceOutOfRange();
        //t04PrintNotReleasedBooksInYear ();
        // t05PrintBooksReleasedBeforeDate();
        // t06PrintAuthorNameEndWith();
        // t07PrintBooksSearchByTitle();
        // t08PrintBooksByAuthorLastNameStartingWith();
        // t09CountBooksByTitleLongerThan();
        // t10PrintAuthorCopies();
        // t11PrintReducedBookByTitle();
        //  t12IncreaseBookCopies();
        //  t13RemoveBookWithCopiesLessThan();
        // t14CallStoredProcedureForBookCount();
    }

    private void t14CallStoredProcedureForBookCount() {

        //  DELIMITER $$
        // CREATE PROCEDURE `udp_books_written_by`(
        //        IN first_n VARCHAR(255),
        //        IN last_n VARCHAR(255),
        //        OUT copies INT)
        // BEGIN
        //	    SELECT count(b.id) INTO copies
        //	    FROM books AS b
        //	    JOIN authors AS a
        //	    ON b.author_id = a.id
        //	    WHERE first_name = first_n AND last_name = last_n;
        // END

        final String firstName = "Amanda";
        final String lastName = "Rice";
        final int count = bookService.getBooksCountFromStoredProcedureByAuthorName(firstName, lastName);
        System.out.printf("%s %s has written %d books", firstName, lastName, count);
    }

    private void t13RemoveBookWithCopiesLessThan() {
        int copies = 260;
        int count = bookService.deleteByCopiesLessThan(copies);
        System.out.printf("Deleted books: %d ", count);
    }

    private void t12IncreaseBookCopies() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr = "12/11/2005";
        LocalDate date = LocalDate.parse(dateStr, formatter);
        int copies = 100;
        int updated = bookService.addCopiesReleaseAfter(copies, date);
        System.out.printf("%d books are released after %s, so total of %d book copies were added",
                updated, dateStr, copies * updated
        );

    }

    private void t11PrintReducedBookByTitle() {
        String title = "Things Fall Apart";
        List<BookTitleTypeRestrictionPrice> ls = bookService.getReducedBooksByTitle(title);
        ls.forEach(b -> System.out.println(b.getInfo()));

    }

    private void t10PrintAuthorCopies() {
        authorService.getAuthorWithCopies().forEach(a -> System.out.println(a));
    }

    private void t09CountBooksByTitleLongerThan() {
        int size = 12;
        int count = bookService.countWithTitleLongerThan(size);
        System.out.printf("There are %d books with longer title than %d symbols", count, size);
    }

    private void t08PrintBooksByAuthorLastNameStartingWith() {
        String contains = "Ric";
        bookService.findAllByAuthorLastNameStartingWith(contains)
                .forEach(System.out::println);
    }

    private void t07PrintBooksSearchByTitle() {
        String contains = "sK";
        bookService.findAllByTitleContaining(contains)
                .forEach(System.out::println);
    }

    private void t06PrintAuthorNameEndWith() {
        String end = "e";
        authorService.findAllByFirstNameEndingWith(end)
                .forEach(System.out::println);
    }

    private void t05PrintBooksReleasedBeforeDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        bookService.findByReleaseDateLessThan(date)
                .forEach(System.out::println);
    }

    private void t04PrintNotReleasedBooksInYear() {
        int year = 1998;
        bookService.findAllTitlesNotReleasedInYear(year).forEach(System.out::println);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateStart = LocalDate.parse("01/01/" + year, formatter);
        LocalDate dateEnd = LocalDate.parse("31/12/" + year, formatter);

        bookService.findByReleaseDateLessThanAndReleaseDateGreaterThan(dateStart, dateEnd).forEach(System.out::println);

    }

    private void t03PrintBookByPriceOutOfRange() {
        BigDecimal min = BigDecimal.valueOf(0.5);
        BigDecimal max = BigDecimal.valueOf(45);

        bookService.findAllByPriceLessThanOrPriceGreaterThan(min, max)
                .forEach(b -> System.out.printf("%s %.2f%n", b.getTitle(), b.getPrice()));
    }

    private void t02BooksByEditionAndCopiesLess() {
        List<String> bookTitles = bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000);
        bookTitles.forEach(System.out::println);
    }

    private void t01BooksByAgeRestriction() {
        //  List<Book> books = bookService.findAllByAgeRestriction(AgeRestriction.ADULT);
        //  books.forEach(b -> System.out.println(b.toString()));

        List<String> bookTitles = bookService.findAllTitlesByAgeRestriction(AgeRestriction.ADULT);
        bookTitles.forEach(System.out::println);
    }


    private void t00_initData() throws IOException {
        this.seedService.seedAll();

    }
}

