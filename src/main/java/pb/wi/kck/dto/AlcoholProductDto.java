package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlcoholProductDto implements Serializable {
    private final Integer alcoholProductId;
    private final int quantity;
    private final int voltage;
    private final FoodProductBlueprintDto foodProductBlueprint;
    private final DealDto deal;
    private final LocationDto location;
}
