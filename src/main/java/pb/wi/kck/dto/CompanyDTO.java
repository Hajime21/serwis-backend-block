package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "companyDtoBuilder")
public class CompanyDTO {
    private Integer companyId;
    private Integer addressId;
    private String companyName;
    private String companyNIP;
}
