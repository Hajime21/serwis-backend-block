package pb.wi.kck.server;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "FoodProductBlueprint")
public class FoodProductBlueprint extends ProductBlueprint {
    private @Id @GeneratedValue int foodProductBlueprintId;
    protected int grammage;                       //4
    protected int kcalPer100g;                    //4
    protected int protein;                        //4
    protected int fat;                            //4
    protected int carbohydrates;                  //4
    protected int productPackage;                 //5

    public FoodProductBlueprint() {}

    @Builder(builderMethodName = "foodProductBlueprintBuilder")
    public FoodProductBlueprint(int foodProductBlueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate, int grammage, int kcalPer100g, int protein, int fat, int carbohydrates, int productPackage) {
        super(name, manufacturer, barcode, barcodeType, description, imgPath, targetQuantity, modificationDate);
        this.foodProductBlueprintId = foodProductBlueprintId;
        this.grammage = grammage;
        this.kcalPer100g = kcalPer100g;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.productPackage = productPackage;
    }

}
