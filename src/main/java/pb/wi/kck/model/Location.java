package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.LocationDto;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    @SequenceGenerator(name = "location_generator", sequenceName = "location_seq")
    private Integer locationId;
    private String locationName;

    public Location(LocationDto locationDto) {
        this.locationId = locationDto.getLocationId();
        this.locationName = locationDto.getLocationName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return locationId != null && Objects.equals(locationId, location.locationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
