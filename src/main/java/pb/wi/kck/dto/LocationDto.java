package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDto implements Serializable {
    private final Integer locationId;
    private final String locationName;
}
