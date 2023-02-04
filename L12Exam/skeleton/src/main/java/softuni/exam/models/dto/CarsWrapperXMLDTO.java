package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWrapperXMLDTO {

    @XmlElement(name = "car")
    private List<CarImportXMLDTO> cars;

    public CarsWrapperXMLDTO() {
    }

    public List<CarImportXMLDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarImportXMLDTO> cars) {
        this.cars = cars;
    }
}
