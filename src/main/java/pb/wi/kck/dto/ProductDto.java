package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductDto implements Serializable {
    private final Integer productId;
    private final LocalDate useByDate;
    private final int quantity;
    private final ProductBlueprintDto productBlueprint;
    private final DealDto deal;
    private final LocationDto location;
}
