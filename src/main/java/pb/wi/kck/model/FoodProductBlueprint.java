package pb.wi.kck.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class FoodProductBlueprint extends ProductBlueprint {
    protected int grammage;                       //4
    protected int kcalPer100g;                    //4
    protected int protein;                        //4
    protected int fat;                            //4
    protected int carbohydrates;                  //4
    protected int productPackage;                 //5

    public FoodProductBlueprint() {}

    @Builder(builderMethodName = "foodProductBlueprintBuilder")
    public FoodProductBlueprint(int blueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate, int grammage, int kcalPer100g, int protein, int fat, int carbohydrates, int productPackage) {
        super(blueprintId, name, manufacturer, barcode, barcodeType, description, imgPath, targetQuantity, modificationDate);
        this.grammage = grammage;
        this.kcalPer100g = kcalPer100g;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.productPackage = productPackage;
    }

    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    public int getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(int kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(int productPackage) {
        this.productPackage = productPackage;
    }

}
