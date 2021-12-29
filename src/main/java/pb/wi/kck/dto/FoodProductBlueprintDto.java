package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FoodProductBlueprintDto implements Serializable {
    private final Integer foodProductBlueprintId;
    private final String name;
    private final String manufacturer;
    private final String description;
    private final String imgPath;
    private final LocalDateTime modificationDate;
    private final int measuredValue;
    private final int kcalPer100;
    private final int protein;
    private final int fat;
    private final int carbohydrates;
    private final FoodProductClassDto foodProductClass;
}
