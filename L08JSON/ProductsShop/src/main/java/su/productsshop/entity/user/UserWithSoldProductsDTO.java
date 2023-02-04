package su.productsshop.entity.user;

import com.google.gson.annotations.Expose;
import su.productsshop.entity.product.ProductSoldDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserWithSoldProductsDTO {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    List<ProductSoldDTO> itemsSold;

    public UserWithSoldProductsDTO() {

        this.itemsSold=new ArrayList<>();
    }

    public UserWithSoldProductsDTO(String firstName, String lastName, List<ProductSoldDTO> itemsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.itemsSold = itemsSold;
    }

    public List<ProductSoldDTO> getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(List<ProductSoldDTO> itemsSold) {

        this.itemsSold = itemsSold.stream().filter(e ->e.getBuyerLastName()!=null).collect(Collectors.toList());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
