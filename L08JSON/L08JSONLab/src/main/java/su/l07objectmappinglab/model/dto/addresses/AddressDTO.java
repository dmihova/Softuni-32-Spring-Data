package su.l07objectmappinglab.model.dto.addresses;

import com.google.gson.annotations.Expose;

public class AddressDTO extends CreateAddressDTO{
    @Expose
    private long id;

    public AddressDTO() {
    }

    public AddressDTO(String country, String city) {
        super(country, city);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
