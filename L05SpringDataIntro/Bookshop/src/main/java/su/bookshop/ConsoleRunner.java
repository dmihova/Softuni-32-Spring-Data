package su.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.bookshop.models.Author;
import su.bookshop.models.Book;
import su.bookshop.repositories.AuthorRepository;
import su.bookshop.repositories.BookRepository;
import su.bookshop.services.SeedService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.t00_initData();
        this.t01_booksAfter2000();
         this.t02_authorsWithBookBefore1990();
         this.t03_authorsByBookCount();
        this.t04_bookByGeorgePowell();
    }



    private void t00_initData() throws IOException {
        this.seedService.seedAll();

    }


    private void t01_booksAfter2000() {
        LocalDate dateAfter = LocalDate.of(2000, 12, 31);
        List<Book> books = bookRepository.findByReleaseDateAfter(dateAfter);
        books.forEach(b -> System.out.println(b.getTitle()));
        System.out.println("Count:" +books.size());
    }
    private void t02_authorsWithBookBefore1990() {
        LocalDate dateBefore = LocalDate.of(1990, 1, 1);

        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(dateBefore);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
        System.out.println("Count: "+ authors.size());

       // int res =bookRepository.countByReleaseDateAfter(dateBefore);
        //System.out.println("Count books :"+ authors.size());
    }
    private void t03_authorsByBookCount() {
        List<Author> authors = authorRepository.findAll();
        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.println("author "+ a.getFirstName()+ " "+a.getLastName()+
                        " books "+                        a.getBooks().size()));

    }

    private void t04_bookByGeorgePowell() {

       // List<Author> authors  = authorRepository.findByFirstName("George");
          Author author = authorRepository.findByFirstNameAndLastName("George", "Powell");
         List<Book> books = bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);
       books.forEach(b -> System.out.printf("%s %s -> %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));
       System.out.println("count: "+ books.size());
    }



}
