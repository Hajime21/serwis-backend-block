package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Company;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CompanyDto implements Serializable {
    private Integer companyId;
    private String companyName;
    private String companyNIP;
    private Integer addressId;

    public CompanyDto(Company company, Integer addressId) {
        this.companyId = company.getCompanyId();
        this.companyName = company.getCompanyName();
        this.companyNIP = company.getCompanyNIP();
        this.addressId = addressId;
    }
}
