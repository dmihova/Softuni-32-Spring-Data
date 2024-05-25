package org.softuni.services;


import org.softuni.entities.Author;
import org.softuni.entities.Book;
import org.softuni.entities.Category;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;
import org.softuni.repositories.AuthorRepository;
import org.softuni.repositories.BookRepository;
import org.softuni.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService{
    private static final  String MODULE ="M02BookShop/";
    private static final String RESOURCE_PATH = MODULE+"src/main/resources/files/";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "categories.txt";
    private static final String BOOKS_FILE_NAME = RESOURCE_PATH + "books.txt";


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService  categoryService;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService  bookService;

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }
        Path currentRelativePath = Paths.get("");
        String s2 = currentRelativePath.toAbsolutePath().toString();
        Files.readAllLines(Path.of(AUTHORS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split("\\s+"))
                .map(names -> new Author(names[0], names[1]))
                .forEach(authorRepository::save);

    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s-> {
                    try {
                        return convertToBook(s);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(bookRepository::save);

    }
    private Book convertToBook(String line) throws ParseException {
        String[] bookParts = line.split("\\s+");

        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];

        LocalDate releaseDate =  LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
    //   Date releaseDate=new SimpleDateFormat("dd/M/yyyy").parse(bookParts[1]);

        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);

        int ageRestrictionIndex = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(bookParts).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set< Category> categories = categoryService.getRandomCategories();

         return  new Book(editionType,releaseDate,copies,price,ageRestriction,title,author,categories);
        }

}
