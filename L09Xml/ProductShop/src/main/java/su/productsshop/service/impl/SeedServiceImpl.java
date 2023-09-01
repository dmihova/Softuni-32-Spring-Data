package su.productsshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.productsshop.entiies.category.Category;
import su.productsshop.entiies.category.ImportCategoriesXMLDTO;
import su.productsshop.entiies.product.Product;
import su.productsshop.entiies.product.ImportProductsXMLDTO;
import su.productsshop.entiies.user.User;
import su.productsshop.entiies.user.ImportUsersXMLDTO;
import su.productsshop.repository.ProductRepository;
import su.productsshop.repository.UserRepository;
import su.productsshop.repository.CategoryRepository;
import su.productsshop.service.SeedService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final  String USER_XML_PATH ="/files/";
    private final  String USER_FILE =USER_XML_PATH +"users.xml";
    private final  String CATEGORY_FILE =USER_XML_PATH+"categories.xml";
    private final  String PRODUCT_FILE =USER_XML_PATH+"products.xml";
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();

    }

    @Override
    public void seedUsers() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(ImportUsersXMLDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream(USER_FILE);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
        ImportUsersXMLDTO importUsersXMLDTO =(ImportUsersXMLDTO) unmarshaller.unmarshal(bfr);

       List<User> users =
                importUsersXMLDTO.getUsers().stream()
                  .map(dto->this.modelMapper.map(dto, User.class))
                .toList();
        //users.stream().forEach(e->System.out.println(e));
        userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws  JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(ImportCategoriesXMLDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream(CATEGORY_FILE);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));

        ImportCategoriesXMLDTO importCategoriesXMLDTO =(ImportCategoriesXMLDTO) unmarshaller.unmarshal(bfr);
        List <Category> categories = importCategoriesXMLDTO.getCategoryNameDTOList().stream()
                .map(cn ->new Category(cn.getName()))
                .collect(Collectors.toList());

         categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws JAXBException {


        JAXBContext jaxbContext = JAXBContext.newInstance(ImportProductsXMLDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream(PRODUCT_FILE);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));

        ImportProductsXMLDTO importProductsXMLDTO =(ImportProductsXMLDTO) unmarshaller.unmarshal(bfr);

        List <Product> products = importProductsXMLDTO.getProducts().stream()
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
