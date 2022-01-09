package pb.wi.kck.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestParam;
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
    //ProductBlueprintDto productBlueprint;
    //FoodProductBlueprintDto foodProductBlueprint;

    public BarcodeDto(Barcode barcode, Integer productBlueprintId) {
        this.barcodeId = barcode.getBarcodeId();
        this.code = barcode.getCode();
        this.type = barcode.getType();
        this.productBlueprintId = productBlueprintId;
    }

}
