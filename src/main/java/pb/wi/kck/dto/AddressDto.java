package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    private final Integer addressId;
    private final String country;
    private final String city;
    private final String street;
    private final String houseNumber;
    private final String localNumber;
    private final String postalCode;
}
