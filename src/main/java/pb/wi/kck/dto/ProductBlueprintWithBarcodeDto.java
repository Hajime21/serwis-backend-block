package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.ProductBlueprintWithBarcode;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ProductBlueprintWithBarcodeDto implements Serializable {
    private Integer productBlueprintId;
    private String productBlueprintName;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;
    private String barcode;
    private String barcodeType;


    public ProductBlueprintWithBarcodeDto(ProductBlueprintWithBarcode productBlueprintWithBarcode) {
        this.productBlueprintId = productBlueprintWithBarcode.getProductBlueprintId();
        this.productBlueprintName = productBlueprintWithBarcode.getProductBlueprintName();
        this.manufacturer = productBlueprintWithBarcode.getManufacturer();
        this.description = productBlueprintWithBarcode.getDescription();
        this.imgPath = productBlueprintWithBarcode.getImgPath();
        this.modificationDate = productBlueprintWithBarcode.getModificationDate();
        this.barcode = productBlueprintWithBarcode.getBarcode();
        this.barcodeType = productBlueprintWithBarcode.getBarcodeType();
    }
}
