package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Barcode;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
//@Builder
public class BarcodeDto implements Serializable {
    private Integer barcodeId;
    private String code;
    private String type;
    private Integer productBlueprintId;
    private Integer foodProductBlueprintId;
    //ProductBlueprintDto productBlueprint;
    //FoodProductBlueprintDto foodProductBlueprint;

    public BarcodeDto(Barcode barcode, Integer productBlueprintId, Integer foodProductBlueprintId) {
        this.barcodeId = barcode.getBarcodeId();
        this.code = barcode.getCode();
        this.type = barcode.getType();
        this.productBlueprintId = productBlueprintId;
        this.foodProductBlueprintId = foodProductBlueprintId;
    }

    public static BarcodeDto barcodeDtoFromFoodProductBlueprint(Barcode barcode, Integer foodProductBlueprintId) {
        return new BarcodeDto(barcode, 0, foodProductBlueprintId);
    }

    public static BarcodeDto barcodeDtoFromGenericProductBlueprint(Barcode barcode, Integer productBlueprintId) {
        return new BarcodeDto(barcode, productBlueprintId, 0);
    }

}
