package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductBlueprintDto;
import pb.wi.kck.dto.ProductBlueprintWithBarcodeDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductBlueprintWithBarcode {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productBlueprintId;
    private String productBlueprintName;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;
    private String barcode;
    private String barcodeType;

    public ProductBlueprintWithBarcode(ProductBlueprintWithBarcodeDto productBlueprintWithBarcodeDto) {
        this.productBlueprintId = productBlueprintWithBarcodeDto.getProductBlueprintId();
        this.productBlueprintName = productBlueprintWithBarcodeDto.getProductBlueprintName();
        this.manufacturer = productBlueprintWithBarcodeDto.getManufacturer();
        this.description = productBlueprintWithBarcodeDto.getDescription();
        this.imgPath = productBlueprintWithBarcodeDto.getImgPath();
        this.modificationDate = productBlueprintWithBarcodeDto.getModificationDate();
        this.barcode = productBlueprintWithBarcodeDto.getBarcode();
        this.barcodeType = productBlueprintWithBarcodeDto.getBarcodeType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductBlueprintWithBarcode that = (ProductBlueprintWithBarcode) o;
        return productBlueprintId != null && Objects.equals(productBlueprintId, that.productBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}