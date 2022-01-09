package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.FoodProduct;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FoodProductDto implements Serializable {
    private Integer foodProductId;
    private LocalDate useByDate;
    private int quantity;
    private Integer foodProductBlueprintId;
    private Integer dealId;
    private Integer locationId;

    public FoodProductDto(FoodProduct foodProduct, Integer foodProductBlueprintId, Integer dealId, Integer locationId) {
        this.foodProductId = foodProduct.getFoodProductId();
        this.useByDate = foodProduct.getUseByDate();
        this.quantity = foodProduct.getQuantity();
        this.foodProductBlueprintId = foodProductBlueprintId;
        this.dealId = dealId;
        this.locationId = locationId;
    }
}
