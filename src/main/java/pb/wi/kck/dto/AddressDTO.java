package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "dealDtoBuilder")
public class AddressDTO {
    private Integer addressId;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String localNumber;
    private String postalCode;
}
