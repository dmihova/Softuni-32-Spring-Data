package su.productsshop.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.productsshop.entity.category.Category;
import su.productsshop.entity.category.CategoryImportDTO;
import su.productsshop.entity.product.Product;
import su.productsshop.entity.product.ProductImportDTO;
import su.productsshop.entity.user.User;
import su.productsshop.entity.user.UserImportDTO;
import su.productsshop.repository.ProductRepository;
import su.productsshop.repository.UserRepository;
import su.productsshop.repository.CategoryRepository;
import su.productsshop.service.SeedService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class SeedServiceImpl implements SeedService {
    private final  String JSON_PATH ="src/main/resources/files/";


    private final  String USER_JSON_PATH =JSON_PATH +"users.json";
    private final  String CATEGORY_JSON_PATH =JSON_PATH+"categories.json";
    private final  String PRODUCT_JSON_PATH =JSON_PATH+"products.json";
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("YYYY-MM-DD")
            //    .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USER_JSON_PATH);

        UserImportDTO [] userImportDTOs = this.gson.fromJson(fileReader, UserImportDTO[].class);

        List<User> users =  Arrays.stream(userImportDTOs)
                .map(dto->this.modelMapper.map(dto, User.class))
                .toList();
        userRepository.saveAll(users);

    }

    @Override
    public void seedCategories() throws FileNotFoundException {

        FileReader fileReader =new FileReader (CATEGORY_JSON_PATH);

        CategoryImportDTO[] categoryImportDTOs = this.gson.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> categories =  Arrays.stream(categoryImportDTOs)
                .map(dto->this.modelMapper.map(dto, Category.class))
                .toList();
        categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws IOException {

        FileReader fileReader =new FileReader (PRODUCT_JSON_PATH);
        ProductImportDTO[] productImportDTOs = this.gson.fromJson(fileReader, ProductImportDTO[].class);


        List<Product> products =  Arrays.stream(productImportDTOs)
                .map(dto->this.modelMapper.map(dto, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .collect(Collectors.toList());


        this.productRepository.saveAllAndFlush(products);
    }




    private Product setRandomSeller(Product product) {
        final User seller = getRandomUser();
        product.setSeller(seller);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {
            User buyer = getRandomUser();
            while (buyer.equals(product.getSeller())) {
                buyer = getRandomUser();
            }
            product.setBuyer(buyer);
        }
        return product;
    }

    private User getRandomUser() {
        long userCount = this.userRepository.count();
        int randomId = new Random().nextInt((int) (userCount))+1;
        Optional<User> randomUser  =this.userRepository.findById((long) randomId);
        while (randomUser.isEmpty()) {
            randomId = new Random().nextInt((int) (userCount+1));
            randomUser  =this.userRepository.findById((long) randomId);
        }
        return randomUser.get();

    }

    private Product setRandomCategories(Product product) {

        long count = this.categoryRepository.count();
        int  random1 = new Random().nextInt((int) (count))+1;
        int  random2 = new Random().nextInt((int) (count))+1;
        final Set<Category> categories = new HashSet<>();

        Optional<Category> randomCategory  =this.categoryRepository.findById((long) random1);
        Optional<Category> randomCategory2  =this.categoryRepository.findById((long) random2);
        categories.add(randomCategory.get());
        categories.add(randomCategory2.get());
        product.setCategories(categories);

        return product;
    }






}
