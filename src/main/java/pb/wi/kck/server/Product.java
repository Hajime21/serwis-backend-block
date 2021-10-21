package pb.wi.kck.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class Product {
    @NonNull private int id;
    @NonNull private String name;
    @NonNull private String description;
    private String barcode;
    private String imgPath;
    private int targetQuantity;

    Product(){}

    Product(String name, String description, String barcode, String imgPath, int targetQuantity) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.imgPath = imgPath;
        this.targetQuantity = targetQuantity;
    }
}