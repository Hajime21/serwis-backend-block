package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Product;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ProductDto implements Serializable {
    private Integer productId;
    private LocalDate useByDate;
    private int quantity;
    private Integer productBlueprint;
    private Integer deal;
    private Integer location;

    public ProductDto(Product product, Integer productBlueprintId, Integer dealId, Integer locationId) {
        this.productId = product.getProductId();
        this.useByDate = product.getUseByDate();
        this.quantity = product.getQuantity();
        this.productBlueprint = productBlueprintId;
        this.deal = dealId;
        this.location = locationId;
    }
}
