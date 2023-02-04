package su.L09XmlLab.model.dto.addresses;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name ="addressXML")
@XmlAccessorType(XmlAccessType.FIELD) // only fields
//@XmlAccessorType(XmlAccessType.PROPERTY) //annotated fields +getter setter
public class AddressXMLDTO implements Serializable {
    @XmlAttribute(name ="id")
    private int id;
    //@XmlTransient  - to exclude
    @XmlElement(name ="country")
    private String country;

    @XmlElement(name ="country-class")
    private CountryXMLDTO CountryXMLDTO;
    @XmlElement(name ="city")
    private String city;

    @XmlElementWrapper(name ="address-details")
    @XmlElement(name ="detail")
    private List<AddressDtlXMLDTO> addressDtlXMLDTOList;

    public AddressXMLDTO() {
        addressDtlXMLDTOList= new ArrayList<>();
    }

    public AddressXMLDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public AddressXMLDTO(int id,CountryXMLDTO countryXMLDTO, String city) {
        this.id = id;
        CountryXMLDTO = countryXMLDTO;
        this.city = city;
    }

    public AddressXMLDTO(int id, String city, String country, List<AddressDtlXMLDTO> addressDtlXMLDTOList) {
        this();
        this.id = id;
        this.country = country;
        this.city = city;
        this.addressDtlXMLDTOList =addressDtlXMLDTOList;
    }

    public AddressXMLDTO(int id,String city, String country,  CountryXMLDTO countryXMLDTO , List<AddressDtlXMLDTO> addressDtlXMLDTOList) {
        this.id = id;
        this.country = country;
        CountryXMLDTO = countryXMLDTO;
        this.city = city;
        this.addressDtlXMLDTOList =addressDtlXMLDTOList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public su.L09XmlLab.model.dto.addresses.CountryXMLDTO getCountryXMLDTO() {
        return CountryXMLDTO;
    }

    public void setCountryXMLDTO(su.L09XmlLab.model.dto.addresses.CountryXMLDTO countryXMLDTO) {
        CountryXMLDTO = countryXMLDTO;
    }

    public List<AddressDtlXMLDTO> getAddressDtlXMLDTOList() {
        return addressDtlXMLDTOList;
    }

    public void setAddressDtlXMLDTOList(List<AddressDtlXMLDTO> addressDtlXMLDTOList) {
        this.addressDtlXMLDTOList = addressDtlXMLDTOList;
    }

    @Override
    public String toString() {
        return "AddressXMLDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", CountryXMLDTO=" + CountryXMLDTO +
                ", city='" + city + '\'' +
                ", addressDtlXMLDTOList=" + addressDtlXMLDTOList +
                '}';
    }
}
