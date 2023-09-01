package su.bookshopadvanced;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.bookshopadvanced.model.entity.AgeRestriction;
import su.bookshopadvanced.model.entity.Book;
import su.bookshopadvanced.model.entity.EditionType;
import su.bookshopadvanced.service.AuthorService;
import su.bookshopadvanced.service.BookService;
import su.bookshopadvanced.service.CategoryService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seedData();
        // e01PrintBooksByAgeRestriction();
        // e01PrintBooksByAgeRestriction2();
        // e02PrintGoldenBooksCopiesLess5000();
        // e03PrintBookByPriceOutOfRange();
        // e04PrintNotReleasedBooksInYear();
        //  e05PrintBooksReleasedBeforeDate();
        //e06PrintAuthroNameEndWith();
        // e07PrintBooksSearchByTitle();
        // e08PrintBooksByAuthorLastNameStartingWith();
        // e09CountBooksByTitleLongerThan();
        // e10PrintAuthorCopies();
        // e11PrintReducedBookByTitle();
        // e12IncreaseBookCopies();
         // e13RemoveBookWithCopiesLessThan();

        e14CallStoredProcedureForBookCount();
    }

    private void e14CallStoredProcedureForBookCount() {

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
        final int count = bookService.getBooksCountFromStoredProcedureByAuthorName(firstName,lastName);
        System.out.printf("%s %s has written %d books", firstName,lastName, count);
    }

    private void e13RemoveBookWithCopiesLessThan() {
        int copies =260;
        int count = bookService.deleteByCopiesLessThan(copies );
        System.out.printf("Deleted books: %d ",            count      );
    }

    private void e12IncreaseBookCopies() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr ="12/11/2005";
        LocalDate date = LocalDate.parse(dateStr, formatter);
        int copies =100;
        int updated = bookService.addCopiesRealeaseAfter(copies,date);
        System.out.printf("%d books are released after %s, so total of %d book copies were added",
                    updated,dateStr,copies*updated
                );

    }

    private void e11PrintReducedBookByTitle() {
         String title ="Things Fall Apart";
        bookService.getReducedBooksByTitle(title)
                .forEach(b->System.out.println(b.getInfo()));

    }

    private void e10PrintAuthorCopies() {
        authorService.getAuthorWithCopies()
                .forEach(a->System.out.println(a.getInfo()));
    }

    private void e09CountBooksByTitleLongerThan() {
        int size =12;
        int count = bookService.countWithTitleLongerThan(size);
                System.out.printf("There are %d books with longer title than %d symbols",count,size);
    }

    private void e08PrintBooksByAuthorLastNameStartingWith() {
        String contains ="Ric";
        bookService.findAllByAuthorLastNameStartingWith(contains)
                .forEach(System.out::println);
    }

    private void e07PrintBooksSearchByTitle() {
        String contains ="sK";
        bookService.findAllTitlesContaining(contains)
                .forEach(System.out::println);
    }

    private void e06PrintAuthroNameEndWith() {
        String end ="e";
        authorService.findAllByFirstNameEndingWith(end)
                .forEach(System.out::println);
    }


    private void e05PrintBooksReleasedBeforeDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2001", formatter);
        bookService.findAllTitlesReleasedBefore(date)
                .forEach(System.out::println);
    }


    private void e04PrintNotReleasedBooksInYear() {
        int year = 1998;
        bookService.findAllTitlesNotReleasedInYear(year)
                .forEach(System.out::println);


    }

    private void e03PrintBookByPriceOutOfRange() {
        BigDecimal min = BigDecimal.valueOf(0.5);
        BigDecimal max = BigDecimal.valueOf(45);

        bookService.findAllByPriceLessThanOrPriceGreaterThan(min, max)
                .forEach(b -> System.out.printf("%s %.2f%n", b.getTitle(), b.getPrice()));
    }

    private void e02PrintGoldenBooksCopiesLess5000() {
        EditionType editionType = EditionType.GOLD;
        int copies = 5000;
        bookService.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .forEach(b -> System.out.println(b.toStringShort()));


    }

    private void e01PrintBooksByAgeRestriction2() {
        String input = "miNor".toUpperCase();
        AgeRestriction inputAgeRestriction = AgeRestriction.valueOf(input);
        bookService.findAllTitlesByAgeRestrictionOptmzQuery(AgeRestriction.MINOR)
                .forEach(System.out::println);
    }


    private void e01PrintBooksByAgeRestriction() {
        String input = "miNor".toUpperCase();
        AgeRestriction inputAgeRestriction = AgeRestriction.valueOf(input);
        bookService.findAllByAgeRestriction(AgeRestriction.MINOR)
                .forEach(System.out::println);
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
