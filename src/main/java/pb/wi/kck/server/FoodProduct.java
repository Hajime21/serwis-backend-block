package pb.wi.kck.server;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FoodProduct extends Product{
    private int grammage;
    private String useByDate;
    private String productPackage;
    private int[] microElements;

    FoodProduct(){}

    FoodProduct(String name, String description, String barcode, String imgPath, int targetQuantity) {
        super(name, description, barcode, imgPath, targetQuantity);
    }
}
