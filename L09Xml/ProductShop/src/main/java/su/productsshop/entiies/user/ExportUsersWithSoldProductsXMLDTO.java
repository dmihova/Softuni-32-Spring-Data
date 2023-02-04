package su.productsshop.entiies.user;

import su.productsshop.entiies.product.ImportProductXMLDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUsersWithSoldProductsXMLDTO {

    @XmlElement(name = "user")
    private List<ExportUserWithSoldProductsXMLDTO> users;

    public ExportUsersWithSoldProductsXMLDTO() {
    }

    public ExportUsersWithSoldProductsXMLDTO(List<ExportUserWithSoldProductsXMLDTO> users) {
        this.users = users;
    }

    public List<ExportUserWithSoldProductsXMLDTO> getUsers() {
        return users;
    }
}
