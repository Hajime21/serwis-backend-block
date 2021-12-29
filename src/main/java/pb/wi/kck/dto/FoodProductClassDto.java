package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FoodProductClassDto implements Serializable {
    private final Integer foodProductClassId;
    private final String foodProductClassName;
    private final int desiredQuantity;
}
