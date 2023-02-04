package softuni.exam.models.dto;


import softuni.exam.util.LocalDateTimeAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportXMLDTO {

    @XmlElement
    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement
    @NotNull
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime date;

    @XmlElement(name = "part")
    @NotNull
    private TaskIdImportXMLDTO part;
    @XmlElement(name = "mechanic")
    @NotNull
    private TaskMechanicNameXMLDTO mechanic;
    @XmlElement(name = "car")
    @NotNull
    private TaskIdImportXMLDTO car;


    public TaskImportXMLDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TaskIdImportXMLDTO getPart() {
        return part;
    }

    public void setPart(TaskIdImportXMLDTO part) {
        this.part = part;
    }

    public TaskMechanicNameXMLDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(TaskMechanicNameXMLDTO mechanic) {
        this.mechanic = mechanic;
    }

    public TaskIdImportXMLDTO getCar() {
        return car;
    }

    public void setCar(TaskIdImportXMLDTO car) {
        this.car = car;
    }
}
