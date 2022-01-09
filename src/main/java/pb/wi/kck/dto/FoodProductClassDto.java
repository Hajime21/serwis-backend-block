package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.FoodProductClass;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FoodProductClassDto implements Serializable {
    private Integer foodProductClassId;
    private String foodProductClassName;
    private int desiredQuantity;

    public FoodProductClassDto(FoodProductClass foodProductClass) {
        this.foodProductClassId = foodProductClass.getFoodProductClassId();
        this.foodProductClassName = foodProductClass.getFoodProductClassName();
        this.desiredQuantity = foodProductClass.getDesiredQuantity();
    }

}
