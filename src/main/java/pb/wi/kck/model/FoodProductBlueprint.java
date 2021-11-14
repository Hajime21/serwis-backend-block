package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductBlueprintBuilder")
@Entity(name = "FoodProductBlueprint")
public class FoodProductBlueprint {
    protected @Id @GeneratedValue @NonNull Integer foodProductBlueprintId;
    protected String name;                        //1
    protected String manufacturer;                //2
    protected String barcode;                     //2
    protected String barcodeType;                 //2
    protected String description;                 //3
    protected String imgPath;                     //3
    protected int targetQuantity;                 //5
    protected LocalDateTime modificationDate;     //5
    protected int grammage;                       //4
    protected int kcalPer100g;                    //4
    protected int protein;                        //4
    protected int fat;                            //4
    protected int carbohydrates;                  //4
    protected int productPackage;                 //5

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
