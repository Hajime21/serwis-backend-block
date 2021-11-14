package pb.wi.kck.server;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "productBlueprintBuilder")
@Entity(name = "ProductBlueprint")
public class ProductBlueprint {
    //funkcjonalności:
    //1 - essential, do bazy danych
    //2 - apka
    //3 - bajery
    //4 - dieta
    //5 - bajery juz ostre
    protected @Id @GeneratedValue int blueprintId;//1
    protected String name;                        //1
    protected String manufacturer;                //2
    protected String barcode;                     //2
    protected String barcodeType;                 //2
    protected String description;                 //3
    protected String imgPath;                     //3
    protected int targetQuantity;                 //5
    protected LocalDateTime modificationDate;     //5

    public ProductBlueprint() {}

    //konstruktor dla dziedziczacych klas (bez blueprintId)
    public ProductBlueprint(String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.barcode = barcode;
        this.barcodeType = barcodeType;
        this.description = description;
        this.imgPath = imgPath;
        this.targetQuantity = targetQuantity;
        this.modificationDate = modificationDate;
    }

}