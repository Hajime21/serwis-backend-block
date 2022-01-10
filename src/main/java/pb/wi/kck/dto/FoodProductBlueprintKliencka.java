package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.FoodProductBlueprint;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FoodProductBlueprintKliencka implements Serializable {
    private Integer foodProductBlueprintId;
    private String name;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;
    private int measuredValue;
    private int kcalPer100;
    private int protein;
    private int fat;
    private int carbohydrates;
    private Integer foodProductClassId;
    private Integer barcodeId;
    private String barcode;
    private String barcodeType;

    public FoodProductBlueprintKliencka(FoodProductBlueprint foodProductBlueprint, Integer foodProductClassId) {
        this.foodProductBlueprintId = foodProductBlueprint.getFoodProductBlueprintId();
        this.name = foodProductBlueprint.getName();
        this.manufacturer = foodProductBlueprint.getManufacturer();
        this.description = foodProductBlueprint.getDescription();
        this.imgPath = foodProductBlueprint.getImgPath();
        this.modificationDate = foodProductBlueprint.getModificationDate();
        this.measuredValue = foodProductBlueprint.getMeasuredValue();
        this.kcalPer100 = foodProductBlueprint.getKcalPer100();
        this.protein = foodProductBlueprint.getProtein();
        this.fat = foodProductBlueprint.getFat();
        this.carbohydrates = foodProductBlueprint.getCarbohydrates();
        this.foodProductClassId = foodProductClassId;
    }
}
