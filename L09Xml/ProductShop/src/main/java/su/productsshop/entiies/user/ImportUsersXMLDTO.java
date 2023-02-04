package su.productsshop.entiies.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name ="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportUsersXMLDTO {

    @XmlElement(name = "user")
    private List<ImportUserXMLDTO> users;

    public ImportUsersXMLDTO() {
        this.users =new ArrayList<>();
    }

    public ImportUsersXMLDTO(List<ImportUserXMLDTO> users) {
        this.users = users;
    }

    public List<ImportUserXMLDTO> getUsers() {
        return users;
    }

    public void setUsers(List<ImportUserXMLDTO> users) {
        this.users = users;
    }
}
