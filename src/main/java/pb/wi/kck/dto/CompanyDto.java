package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyDto implements Serializable {
    private final Integer companyId;
    private final String companyName;
    private final String companyNIP;
    private final AddressDto address;
}
