package pb.wi.kck.server;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;

@Entity
@Builder
@Data
public class FoodProductBlueprint extends ProductBlueprint {
    private long useByDate;                     //4
    private int grammage;                       //4
    private int kcalPer100g;                    //4
    private int protein;                        //4
    private int fat;                            //4
    private int carbohydates;                   //4
    //private int productPackage;               //5

    FoodProductBlueprint(){
        super();
    }

    //FoodProductBlueprint(String name, String description, String barcode, String imgPath, int targetQuantity) {
        //super(name, description, barcode, imgPath, targetQuantity);
    }
}
