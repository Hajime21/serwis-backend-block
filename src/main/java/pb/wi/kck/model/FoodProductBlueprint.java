package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FoodProductBlueprint {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductBlueprintId;
    private String name;
    private String manufacturer;
    //private String barcode;
    //private String barcodeType;
    private String description;
    //private String ingredients;
    private String imgPath;
    private LocalDateTime modificationDate;
    private int measuredValue;
    private int kcalPer100;
    private int protein;
    private int fat;
    private int carbohydrates;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "FOOD_PRODUCT_CLASS_ID", nullable = false)
    private FoodProductClass foodProductClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FoodProductBlueprint that = (FoodProductBlueprint) o;
        return foodProductBlueprintId != null && Objects.equals(foodProductBlueprintId, that.foodProductBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
