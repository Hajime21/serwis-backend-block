package pb.wi.kck.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "dealDtoBuilder")
public class DealDTO {
    private Integer dealId;
    private Integer userId;
    private Integer companyId;
    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private String documentName;
}
