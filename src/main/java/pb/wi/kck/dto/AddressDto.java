package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Address;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class AddressDto implements Serializable {
    private Integer addressId;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String localNumber;
    private String postalCode;

    public AddressDto(Address address) {
        this.addressId = address.getAddressId();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.localNumber = address.getLocalNumber();
        this.postalCode = address.getPostalCode();
    }
}
