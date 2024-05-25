package com.softuni;

import com.softuni.models.entities.Author;
import com.softuni.models.entities.Book;
import com.softuni.services.AuthorService;
import com.softuni.services.BookService;
import com.softuni.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


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
        //t00_initData();

        // this.t01BooksAfter2000();
        // this.t02AuthorsWithBookBefore1990();
      //  this.t03AuthorsByBookCount();
         this.t04BookByGeorgePowell();

    }

    private void t04BookByGeorgePowell() {
        // List<Author> authors  = authorRepository.findByFirstName("George");
        Author author = authorService.findByFirstNameAndLastName("George", "Powell");
        List<Book> books = bookService.findByAuthorOrderByReleaseDateDescTitleAsc(author);
        books.forEach(b -> System.out.printf("%s %s -> %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));
        System.out.println("count: "+ books.size());
    }

    private void t03AuthorsByBookCount() {
        List<Author> authors = authorService.findAll();
        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.println("author " + a.getFirstName() + " " + a.getLastName() +
                        " books " + a.getBooks().size()));

    }

    private void t02AuthorsWithBookBefore1990() {
        LocalDate dateBefore = LocalDate.of(1990, 1, 1);

        List<Author> authors = authorService.findDistinctByBooksReleaseDateBefore(dateBefore);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
        System.out.println("Count: " + authors.size());

        // int res =bookRepository.countByReleaseDateAfter(dateBefore);
        //System.out.println("Count books :"+ authors.size());
    }


    private void t01BooksAfter2000() {
        LocalDate dateAfter = LocalDate.of(2000, 12, 31);
        List<Book> books = bookService.findByReleaseDateAfter(dateAfter);
        books.forEach(b -> System.out.println(b.getTitle()));
        System.out.println("Count:" + books.size());
    }

    private void t00_initData() throws IOException {
        this.seedService.seedAll();

    }
}

