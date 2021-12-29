package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class FoodProductDto implements Serializable {
    private final Integer foodProductId;
    private final LocalDate useByDate;
    private final int quantity;
    private final FoodProductBlueprintDto foodProductBlueprint;
    private final DealDto deal;
    private final LocationDto location;
}
