package pb.wi.kck.server;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder(builderMethodName = "productBlueprintBuilder")
public class ProductBlueprint {
    //funkcjonalności:
    //1 - essential, do bazy danych
    //2 - apka
    //3 - bajery
    //4 - dieta
    //5 - bajery juz ostre
    protected int blueprintId;                    //1
    protected String name;                        //1
    protected String manufacturer;                //2
    protected String barcode;                     //2
    protected String barcodeType;                 //2
    protected String description;                 //3
    protected String imgPath;                     //3
    protected int targetQuantity;                 //3
    protected LocalDateTime modificationDate;              //3

    public ProductBlueprint() {}

}