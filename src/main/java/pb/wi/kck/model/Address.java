package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.AddressDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer addressId;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String localNumber;
    private String postalCode;

    public Address(AddressDto addressDto) {
        this.addressId = addressDto.getAddressId();
        this.country = addressDto.getCountry();
        this.city = addressDto.getCity();
        this.street = addressDto.getStreet();
        this.houseNumber = addressDto.getHouseNumber();
        this.localNumber = addressDto.getLocalNumber();
        this.postalCode = addressDto.getPostalCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return addressId != null && Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
