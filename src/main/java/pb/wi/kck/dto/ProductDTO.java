package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.ProductBlueprint;

import java.time.LocalDate;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "productDtoBuilder")
public class ProductDTO {
    private Integer productId;
    private Integer productBlueprintId;
    private Integer dealId;
    private Integer locationId;
    private LocalDate useByDate;
    private int quantity;
    //private ProductBlueprint dependedProductBlueprint;

}
