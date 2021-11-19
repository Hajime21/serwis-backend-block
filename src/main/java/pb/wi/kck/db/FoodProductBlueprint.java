package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FoodProductBlueprint {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductBlueprintId;
    private String name;
    private String manufacturer;
    private String barcode;
    private String barcodeType;
    private String description;
    private String ingredients;
    private String imgPath;
    private LocalDateTime modificationDate;
    private int measuredValue;
    private int kcalPer100;
    private int protein;
    private int fat;
    private int carbohydrates;

}
