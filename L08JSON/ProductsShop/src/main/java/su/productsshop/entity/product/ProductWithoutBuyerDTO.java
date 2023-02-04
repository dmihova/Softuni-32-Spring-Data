package su.productsshop.entity.product;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class ProductWithoutBuyerDTO {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose

    private String seller;

    public ProductWithoutBuyerDTO(String name, BigDecimal price, String fname,String lname) {
        this.name = name;
        this.price = price;
        if (fname==null) {
            this.seller = lname;
        }
        else {
            this.seller = fname +" " +lname;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
