package pb.wi.kck.server.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FoodProductBlueprintDTO extends ProductBlueprintDTO {
    protected int grammage;
    protected int kcalPer100g;
    protected int protein;
    protected int fat;
    protected int carbohydrates;
    protected int productPackage;

    @Builder(builderMethodName = "foodProductBlueprintDTOBuilder")
    public FoodProductBlueprintDTO(int blueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate) {
        super(blueprintId, name, manufacturer, barcode, barcodeType, description, imgPath, targetQuantity, modificationDate);
    }

    public int getGrammage() {
        return grammage;
    }

    public int getKcalPer100g() {
        return kcalPer100g;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getProductPackage() {
        return productPackage;
    }

}