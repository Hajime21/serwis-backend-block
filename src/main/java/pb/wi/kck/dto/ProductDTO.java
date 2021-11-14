package pb.wi.kck.server.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "productDTOBuilder")
public class ProductDTO {
    protected final int productId;
    protected final int blueprintId;
    protected final int receiptId;
    protected final int invoiceId;
    protected final LocalDate useByDate;
    protected final int quantity; //czy na pewno?
    protected final String location;

}
