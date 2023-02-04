package su.productsshop.entiies.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductsXMLDTO {

    @XmlElement(name = "product")
    private List<ImportProductXMLDTO> products;

    public ImportProductsXMLDTO() {
    }

    public List<ImportProductXMLDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ImportProductXMLDTO> products) {
        this.products = products;
    }
}

