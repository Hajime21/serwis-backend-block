package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.FoodProductDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FoodProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_product_generator")
    @SequenceGenerator(name = "food_product_generator", sequenceName = "food_product_seq")
    private Integer foodProductId;
    private LocalDate useByDate;
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FOOD_PRODUCT_BLUEPRINT_ID", nullable = false)
    private FoodProductBlueprint foodProductBlueprint;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEAL_ID", nullable = false)
    private Deal deal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    public FoodProduct(FoodProductDto foodProductDto, FoodProductBlueprint foodProductBlueprint, Deal deal, Location location) {
        this.foodProductId = foodProductDto.getFoodProductId();
        this.useByDate = foodProductDto.getUseByDate();
        this.quantity = foodProductDto.getQuantity();
        this.foodProductBlueprint = foodProductBlueprint;
        this.deal = deal;
        this.location = location;
    }

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
