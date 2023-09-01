package su.bookshopadvanced.service.impl;

import org.springframework.stereotype.Service;
import su.bookshopadvanced.model.entity.*;
import su.bookshopadvanced.repository.BookRepository;
import su.bookshopadvanced.service.AuthorService;
import su.bookshopadvanced.service.BookService;
import su.bookshopadvanced.service.CategoryService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
          return bookRepository
                .findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<String> findAllTitlesByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository
                .findAllByAgeRestriction(ageRestriction)   .stream()
                .map(book -> String.format("%s ", book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType,copies);
    }

    @Override
    public List<String> findAllTitlesByAgeRestrictionOptmzQuery(AgeRestriction ageRestriction) {
           return bookRepository
                .findAllTitlesByAgeRestrictionOptmzQuery(ageRestriction)  ;
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(min,max);
    }

    @Override
    public List<String> findAllTitlesNotReleasedInYear(int year) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate startDate = LocalDate.parse("01/01/"+year, formatter);
        LocalDate endDate=LocalDate.parse("31/12/"+year, formatter);

        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(startDate,endDate)
                .stream()
                .map(book -> String.format("%s ", book.getTitle()))
                .collect(Collectors.toList());
    }


    @Override
    public List<String> findAllTitlesReleasedBefore(LocalDate date) {
        return bookRepository.findAllByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s %.2f", book.getTitle(),book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTitlesContaining(String contains) {
          return bookRepository.findAllByTitleContaining(contains)
                .stream()
                .map(book -> String.format("%s ", book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByAuthorLastNameStartingWith(String lastName) {
        return bookRepository.findAllByAuthorLastNameStartingWith(lastName)
                .stream()
                .map(book -> String.format("%s -  %s", book.getTitle(),book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int countWithTitleLongerThan(int size) {
        return bookRepository.countByTitleLongerThan(size);
    }

    @Override
    public List<BookTitleTypeRestrictionPrice> getReducedBooksByTitle(String title) {
        return bookRepository.getReducedBooksByTitle(title);
    }

    @Override
    @Transactional
    public int addCopiesRealeaseAfter(int copies, LocalDate date) {
        return bookRepository.addCopiesRealeaseAfter(copies,date);
    }

    @Override
    @Transactional
    public int deleteByCopiesLessThan(int copies) {
        return bookRepository.deleteByCopiesLessThan(copies);
    }

    @Override
    public int getBooksCountFromStoredProcedureByAuthorName(String firstName,String lastName) {
        return bookRepository.countBooksWrittenByAuthorStoredProcedure(firstName,lastName);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
