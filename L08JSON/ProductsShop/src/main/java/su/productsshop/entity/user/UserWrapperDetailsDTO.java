package su.productsshop.entity.user;

import su.productsshop.entity.product.ProductWrapperDTO;

public class UserWrapperDetailsDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private ProductWrapperDTO soldProducts;

    public int soldProductsCount(){
        return soldProducts.getCount();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductWrapperDTO getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductWrapperDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}