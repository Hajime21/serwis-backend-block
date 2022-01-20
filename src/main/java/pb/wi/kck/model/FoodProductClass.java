package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.FoodProductClassDto;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FoodProductClass {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductClassId;
    private String foodProductClassName;
    private int desiredQuantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodProductClass")
    @ToString.Exclude
    private Set<FoodProductBlueprint> foodProductBlueprints = new LinkedHashSet<>();

    public FoodProductClass(FoodProductClassDto foodProductClassDto) {
        this.foodProductClassId = foodProductClassDto.getFoodProductClassId();
        this.foodProductClassName = foodProductClassDto.getFoodProductClassName();
        this.desiredQuantity = foodProductClassDto.getDesiredQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FoodProductClass that = (FoodProductClass) o;
        return foodProductClassId != null && Objects.equals(foodProductClassId, that.foodProductClassId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
