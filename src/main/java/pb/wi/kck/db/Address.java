package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer addressId;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String localNumber;
    private String postalCode;

}
