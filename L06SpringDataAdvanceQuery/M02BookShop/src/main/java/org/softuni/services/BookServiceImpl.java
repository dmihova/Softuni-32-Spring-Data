package org.softuni.services;


import org.softuni.entities.Author;
import org.softuni.entities.Book;
import org.softuni.entities.dto.BookTitleTypeRestrictionPrice;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;
import org.softuni.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByReleaseDateAfter(LocalDate dateAfter) {
        return bookRepository.findByReleaseDateAfter(dateAfter);
    }

    @Override
    public List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author) {
        return bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<String> findAllTitlesByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllTitlesByAgeRestriction(ageRestriction);
    }

    @Override
    public List<String> findAllTitlesByEditionAndCopies(EditionType editionType, int copies) {
        return bookRepository.findAllTitlesByEditionAndCopies(editionType,copies);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(min,max);
    }

    @Override
    public List<String> findAllTitlesNotReleasedInYear(int year) {
        return bookRepository.findAllTitlesNotReleasedInYear(year);
    }

    @Override
    public List<String> findByReleaseDateLessThanAndReleaseDateGreaterThan(LocalDate dateStart, LocalDate dateEnd) {
        return bookRepository.findByReleaseDateLessThanAndReleaseDateGreaterThan(dateStart,dateEnd);
    }

    @Override
    public List<Book> findByReleaseDateLessThan(LocalDate date) {
        return bookRepository.findAllByReleaseDateLessThan(date);
    }

    @Override
    public List<Book> findAllByTitleContaining(String contains) {
        return bookRepository.findAllByTitleContaining(contains);
    }


    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String start) {
        return bookRepository.findAllByAuthorLastNameStartingWith(start);
    }

    @Override
    public int countWithTitleLongerThan(int size) {
        return bookRepository.countWithTitleLongerThan(size);
    }

    @Override
    public List<BookTitleTypeRestrictionPrice> getReducedBooksByTitle(String title) {
        return bookRepository.findAllByTitleReduced(title);
    }

    @Override
    @Transactional
    @Modifying
    public int addCopiesReleaseAfter(int copies, LocalDate date) {
        return bookRepository.addCopiesReleaseAfter(copies,date);
    }

    @Override
    @Transactional
    public int deleteByCopiesLessThan(int copies) {
        return bookRepository.deleteByCopiesLessThan(copies);
    }

    @Override
    public int getBooksCountFromStoredProcedureByAuthorName(String firstName, String lastName) {
           return bookRepository.countBooksWrittenByAuthorStoredProcedure(firstName,lastName);
    }


}
