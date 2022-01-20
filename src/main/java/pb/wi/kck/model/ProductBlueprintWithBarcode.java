package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductBlueprintWithBarcodeDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productBlueprintWithBarcode", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ProductOld> productOlds = new LinkedHashSet<>();

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