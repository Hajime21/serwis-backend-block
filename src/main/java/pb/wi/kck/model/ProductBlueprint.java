package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductBlueprintDto;

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
public class ProductBlueprint {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productBlueprintId;
    private String productBlueprintName;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;

    public ProductBlueprint(ProductBlueprintDto productBlueprintDto) {
        this.productBlueprintId = productBlueprintDto.getProductBlueprintId();
        this.productBlueprintName = productBlueprintDto.getProductBlueprintName();
        this.manufacturer = productBlueprintDto.getManufacturer();
        this.description = productBlueprintDto.getDescription();
        this.imgPath = productBlueprintDto.getImgPath();
        this.modificationDate = productBlueprintDto.getModificationDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductBlueprint that = (ProductBlueprint) o;
        return productBlueprintId != null && Objects.equals(productBlueprintId, that.productBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}