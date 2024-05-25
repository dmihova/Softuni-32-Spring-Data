package com.softuni.repositories;

import com.softuni.models.entities.Author;
import com.softuni.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

    List<Book> findByReleaseDateAfter(LocalDate dateAfter);

    int countByReleaseDateAfter(LocalDate dateBefore);

    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
