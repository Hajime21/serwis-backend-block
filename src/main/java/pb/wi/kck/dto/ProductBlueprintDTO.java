package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "productBlueprintDTOBuilder")
public class ProductBlueprintDTO {
    protected Integer productBlueprintId;
    protected String name;
    protected String manufacturer;
    protected String barcode;
    protected String barcodeType;
    protected String description;
    protected String imgPath;
    protected int targetQuantity;
    protected LocalDateTime modificationDate;
    protected List<Product> dependingProducts;

}
