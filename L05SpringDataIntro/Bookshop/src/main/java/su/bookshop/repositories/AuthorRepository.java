package su.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.bookshop.models.Author;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate dateBefore);

    List<Author> findByFirstName(String name);

    Author findByFirstNameAndLastName(String george, String powell);
}
