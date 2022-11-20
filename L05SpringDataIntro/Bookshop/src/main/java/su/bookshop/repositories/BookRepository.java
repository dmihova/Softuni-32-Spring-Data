package su.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.bookshop.models.Author;
import su.bookshop.models.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

    List<Book> findByReleaseDateAfter(LocalDate dateAfter);

    int countByReleaseDateAfter(LocalDate dateBefore);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
