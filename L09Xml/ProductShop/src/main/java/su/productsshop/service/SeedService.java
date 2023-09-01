package su.productsshop.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws FileNotFoundException, JAXBException;

    void seedCategories() throws  JAXBException;

    void seedProducts() throws IOException, JAXBException;


    default void seedAll() throws IOException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
