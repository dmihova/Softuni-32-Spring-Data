package su.productsshop.entiies.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportProductsInRangeXMLDTO {

    @XmlElement(name = "product")
    private List<ExportProductXMLDTO> products;

    public ExportProductsInRangeXMLDTO() {
    }

    public ExportProductsInRangeXMLDTO(List<ExportProductXMLDTO> exportProductXMLDTOS) {
        this.products= exportProductXMLDTOS;
    }

    public List<ExportProductXMLDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ExportProductXMLDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductsInRangeXMLDTO{" +
                "products=" + products +
                '}';
    }
}
