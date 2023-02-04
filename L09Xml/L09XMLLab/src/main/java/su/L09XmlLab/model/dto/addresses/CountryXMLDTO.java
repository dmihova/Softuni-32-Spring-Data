package su.L09XmlLab.model.dto.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="country")
@XmlAccessorType(XmlAccessType.FIELD) // only fields
public class CountryXMLDTO {

    @XmlAttribute
    private String name;

    public CountryXMLDTO() {
    }

    public CountryXMLDTO(String name) {
        this.name = name;
    }
}
