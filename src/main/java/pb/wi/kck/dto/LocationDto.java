package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Location;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LocationDto implements Serializable {
    private Integer locationId;
    private String locationName;

    public LocationDto(Location location) {
        this.locationId = location.getLocationId();
        this.locationName = location.getLocationName();
    }
}
