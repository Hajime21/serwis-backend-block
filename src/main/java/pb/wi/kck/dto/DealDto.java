package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DealDto implements Serializable {
    private final Integer dealId;
    private final LocalDate purchaseDate;
    private final BigDecimal purchaseValue;
    private final String documentName;
    private final UserDto user;
    private final CompanyDto company;
}
