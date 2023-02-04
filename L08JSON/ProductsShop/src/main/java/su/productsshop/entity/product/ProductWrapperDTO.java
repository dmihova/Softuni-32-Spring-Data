package su.productsshop.entity.product;

import java.util.List;

public class ProductWrapperDTO {
    private Integer count;
    private List<ProductWrappedDTO> products;

    public ProductWrapperDTO(List<ProductWrappedDTO> products) {
        this.products = products;
        this.count = products.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductWrappedDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWrappedDTO> products) {
        this.products = products;
    }
}
