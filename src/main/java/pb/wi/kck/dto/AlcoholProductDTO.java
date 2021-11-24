package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "alcoholProductDtoBuilder")
public class AlcoholProductDTO {
    private Integer alcoholProductId;
    private Integer foodProductBlueprintId;
    private Integer dealId;
    private Integer locationId;
    private int quantity;
    private int voltage;
}
