package pb.wi.kck.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Value //@Data
@Builder
public class BarcodeDto implements Serializable {
    Integer barcodeId;
    String code;
    String type;
    ProductBlueprintDto productBlueprint;
    //FoodProductBlueprintDto foodProductBlueprint;
}
