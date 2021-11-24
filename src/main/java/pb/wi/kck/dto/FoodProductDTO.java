package pb.wi.kck.dto;

import lombok.*;

import java.time.LocalDate;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductDtoBuilder")
public class FoodProductDTO {
    private Integer foodProductId;
    private Integer foodProductBlueprintId;
    private Integer dealId;
    private Integer locationId;
    private LocalDate useByDate;
    private int quantity;

}
