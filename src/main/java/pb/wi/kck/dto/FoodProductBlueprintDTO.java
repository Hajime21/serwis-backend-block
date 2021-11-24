package pb.wi.kck.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductBlueprintDtoBuilder")
public class FoodProductBlueprintDTO {
    private Integer foodProductBlueprintId;
    private Integer foodProductClassId;
    private String name;
    private String manufacturer;
    private String barcode;
    private String barcodeType;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;
    private int measuredValue;
    private int kcalPer100;
    private int protein;
    private int fat;
    private int carbohydrates;

}
