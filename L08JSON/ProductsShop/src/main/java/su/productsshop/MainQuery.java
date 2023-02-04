package su.productsshop;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.productsshop.entity.product.ProductWithoutBuyerDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTOI;
import su.productsshop.entity.user.UsersCountWrapperDTO;
import su.productsshop.service.ProductService;
import su.productsshop.service.UserService;

import java.util.List;

@Component
public class MainQuery implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;
    public final Gson gson;

    public MainQuery(ProductService productService, UserService userService, Gson gson) {
        this.productService = productService;
        this.userService = userService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        // productsInRangeI();
        // productsInRange();
       // userSoldProducts();
      //  getCategoryStatistics();
        getUserCountProductCount();
    }

    private void getUserCountProductCount() {

        UsersCountWrapperDTO usersCountWrapperDto = this.userService.findUsersSoldProductsWithCount();




    }

    private void getCategoryStatistics() {
        this.productService.getCategoryStatistics().stream().forEach(dto ->System.out.println(this.gson.toJson(dto) ));
    }

    private void userSoldProducts() {
        this.userService.getUserWithSoldProducts().stream().forEach(dto ->System.out.println(this.gson.toJson(dto) ));

    }

    private void productsInRange() {
        List<ProductWithoutBuyerDTO> products=
                this.productService.
                        getProductsInPriceRangeForSell(500 , 1000  );
        products.forEach(dto ->System.out.println(this.gson.toJson(dto) ));
    }

    private void productsInRangeI() {
        List<ProductWithoutBuyerDTOI> products=
                    this.productService.
                            getProductsInPriceRangeForSellI(500 , 1000  );
        products.forEach(dto ->System.out.println(dto.getSeller() ));
    }
}
