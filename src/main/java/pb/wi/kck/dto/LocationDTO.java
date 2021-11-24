package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "locationDtoBuilder")
public class LocationDTO {
    private Integer locationId;
    private String locationName;
}
