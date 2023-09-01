package su.productsshop;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.productsshop.entiies.product.ExportProductsInRangeXMLDTO;
import su.productsshop.entiies.user.ExportUsersWithSoldProductsXMLDTO;
import su.productsshop.service.ProductService;
import su.productsshop.service.UserService;

import javax.xml.bind.*;
import java.io.File;

@Component
public class MainQuery implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;


    public MainQuery(ProductService productService, UserService userService, Gson gson) {
        this.productService = productService;
        this.userService = userService;
      }

    @Override
    public void run(String... args) throws Exception {
        //e1GetProductsInRange();
        e2GetusersWithSoldProducts();

    }

    private void e2GetusersWithSoldProducts() throws JAXBException {
        ExportUsersWithSoldProductsXMLDTO products=    this.userService.findAllWithSoldProducts();
        JAXBContext jaxbContext = JAXBContext.newInstance(ExportUsersWithSoldProductsXMLDTO.class);
        Marshaller  marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(products,new File("e2_user_sold_products.xml"));
    }

    private void e1GetProductsInRange() throws JAXBException {
        ExportProductsInRangeXMLDTO products=
                this.productService.
                        getInPriceRangeForSell(500.0 , 1000.0  );

        JAXBContext jaxbContext = JAXBContext.newInstance(ExportProductsInRangeXMLDTO.class);
         Marshaller  marshaller = jaxbContext.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
         marshaller.marshal(products,new File("e1_products.xml"));
    }
}
