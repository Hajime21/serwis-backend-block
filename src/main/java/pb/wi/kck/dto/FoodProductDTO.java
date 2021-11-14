package pb.wi.kck.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "foodProductDTOBuilder")
public class FoodProductDTO {
    protected final int foodProductId;
    protected final int blueprintId;
    protected final int receiptId;
    protected final int invoiceId;
    protected final LocalDate useByDate;
    protected final int quantity; //czy na pewno?
    protected final String location;

}
