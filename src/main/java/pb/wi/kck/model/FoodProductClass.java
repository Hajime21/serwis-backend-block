package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductClassBuilder")
@Entity
public class FoodProductClass {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductClassId;
    private String foodProductClassName;
    private int desiredQuantity;

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
