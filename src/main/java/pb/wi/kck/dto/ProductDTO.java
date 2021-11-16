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
@Builder(builderMethodName = "productDTOBuilder")
public class ProductDTO {
    protected Integer productId;
    //protected int blueprintId;
    protected int receiptId;
    protected int invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;
    protected ProductBlueprint dependedProductBlueprint;

}
