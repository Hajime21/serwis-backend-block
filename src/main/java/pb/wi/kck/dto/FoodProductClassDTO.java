package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductClassDtoBuilder")
public class FoodProductClassDTO {
    private Integer foodProductClassId;
    private String foodProductClassName;
    private int desiredQuantity;
}
