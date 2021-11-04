package pb.wi.kck.server;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
public class FoodProductBlueprint extends ProductBlueprint {
    protected int grammage;                       //4
    protected int kcalPer100g;                    //4
    protected int protein;                        //4
    protected int fat;                            //4
    protected int carbohydrates;                  //4
    protected int productPackage;                 //5

    public FoodProductBlueprint() {}

    @Id
    @Override
    public int getBlueprintId() {
        return super.getBlueprintId();
    }

    @Builder(builderMethodName = "foodProductBlueprintBuilder")
    public FoodProductBlueprint(int blueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, long modificationDate, int grammage, int kcalPer100g, int protein, int fat, int carbohydrates, int productPackage) {
        super(blueprintId, name, manufacturer, barcode, barcodeType, description, imgPath, targetQuantity, modificationDate);
        this.grammage = grammage;
        this.kcalPer100g = kcalPer100g;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.productPackage = productPackage;
    }

}
