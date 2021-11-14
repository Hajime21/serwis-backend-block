package pb.wi.kck.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "foodProductBlueprintDTOBuilder")
public class FoodProductBlueprintDTO {
    protected final int blueprintId;
    protected final String name;
    protected final String manufacturer;
    protected final String barcode;
    protected final String barcodeType;
    protected final String description;
    protected final String imgPath;
    protected final int targetQuantity;
    protected final LocalDateTime modificationDate;
    protected final int grammage;
    protected final int kcalPer100g;
    protected final int protein;
    protected final int fat;
    protected final int carbohydrates;
    protected final int productPackage;

}
