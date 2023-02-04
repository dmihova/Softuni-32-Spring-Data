package su.productsshop.entiies.user;

import su.productsshop.entiies.product.ExportSoldProductXMLDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldProductsXMLDTO {
    @XmlAttribute(name ="first-name")
    private String firstName;

    @XmlAttribute(name ="last-name" )
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ExportSoldProductXMLDTO> itemsSold;

    public ExportUserWithSoldProductsXMLDTO() {
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

    public List<ExportSoldProductXMLDTO> getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(List<ExportSoldProductXMLDTO> itemsSold) {
        this.itemsSold = itemsSold;
    }
}
