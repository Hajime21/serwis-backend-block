package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductBuilder")
@Entity
public class FoodProduct {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductId;
    private LocalDate useByDate;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "FOOD_PRODUCT_BLUEPRINT_ID", nullable = false)
    private FoodProductBlueprint foodProductBlueprint;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "DEAL_ID", nullable = false)
    private Deal deal;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FoodProduct that = (FoodProduct) o;
        return foodProductId != null && Objects.equals(foodProductId, that.foodProductId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
