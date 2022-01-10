package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.ProductOld;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ProductOldDto implements Serializable {
    private Integer productId;
    private LocalDate useByDate;
    private int quantity;
    private String location;
    private Integer productBlueprintWithBarcodeId;

    public ProductOldDto(ProductOld productOld, Integer productBlueprintWithBarcodeId) {
        this.productId = productOld.getProductId();
        this.useByDate = productOld.getUseByDate();
        this.quantity = productOld.getQuantity();
        this.location = productOld.getLocation();
        this.productBlueprintWithBarcodeId = productBlueprintWithBarcodeId;
    }
}
