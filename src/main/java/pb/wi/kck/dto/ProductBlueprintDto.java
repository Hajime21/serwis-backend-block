package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.ProductBlueprint;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ProductBlueprintDto implements Serializable {
    Integer productBlueprintId;
    String productBlueprintName;
    String manufacturer;
    String description;
    String imgPath;
    LocalDateTime modificationDate;

    public ProductBlueprintDto(ProductBlueprint productBlueprint) {
        this.productBlueprintId = productBlueprint.getProductBlueprintId();
        this.productBlueprintName = productBlueprint.getProductBlueprintName();
        this.manufacturer = productBlueprint.getManufacturer();
        this.description = productBlueprint.getDescription();
        this.imgPath = productBlueprint.getImgPath();
        this.modificationDate = productBlueprint.getModificationDate();
    }
}
