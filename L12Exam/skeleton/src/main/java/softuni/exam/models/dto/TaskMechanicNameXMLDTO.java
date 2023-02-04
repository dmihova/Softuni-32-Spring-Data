package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "firstName")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskMechanicNameXMLDTO {

    @XmlElement
    @NotNull
    private String firstName;

    public TaskMechanicNameXMLDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
