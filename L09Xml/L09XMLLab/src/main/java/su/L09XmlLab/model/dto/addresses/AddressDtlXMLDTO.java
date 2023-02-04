package su.L09XmlLab.model.dto.addresses;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDtlXMLDTO {
    @XmlAttribute
    private String name;

    public AddressDtlXMLDTO() {
    }

    public AddressDtlXMLDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddressDtlXMLDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
