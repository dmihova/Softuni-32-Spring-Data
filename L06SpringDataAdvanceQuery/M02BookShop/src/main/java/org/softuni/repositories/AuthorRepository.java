package org.softuni.repositories;


import org.softuni.entities.Author;
import org.softuni.entities.dto.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate dateBefore);

    List<Author> findByFirstName(String name);

    Author  findByFirstNameAndLastName(String firstName, String lastName);


   List<Author> findAllByFirstNameEndingWith(String end);

    @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS totalCopies "+
            "FROM Author a " +
            "JOIN a.books AS b " +
            "GROUP BY b.author " +
            "ORDER BY totalCopies DESC")
    List<AuthorNamesWithTotalCopies> findAllWithTotalCopies();

}
