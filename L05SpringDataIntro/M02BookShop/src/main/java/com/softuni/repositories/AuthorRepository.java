package com.softuni.repositories;

import com.softuni.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate dateBefore);

    List<Author> findByFirstName(String name);

    Author  findByFirstNameAndLastName(String firstName, String lastName);
}
