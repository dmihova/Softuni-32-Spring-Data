package su.productsshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    void seedProducts() throws IOException;


    default void seedAll() throws IOException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
