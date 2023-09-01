package su.l07objectmappinglab.model.dto;

import javax.persistence.Column;

public class CreateAddressDTO {

    private String country;
    private String city;

    public CreateAddressDTO() {
    }

    public CreateAddressDTO(String country, String city) {
        this.country = country;
        this.city = city;
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
}
