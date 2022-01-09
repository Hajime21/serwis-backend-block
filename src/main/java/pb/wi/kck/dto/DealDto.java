package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.Deal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class DealDto implements Serializable {
    private Integer dealId;
    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private String documentName;
    private Integer userId;
    private Integer companyId;

    public DealDto(Deal deal, Integer companyId, Integer userId) {
        this.dealId = deal.getDealId();
        this.purchaseDate = deal.getPurchaseDate();
        this.purchaseValue = deal.getPurchaseValue();
        this.documentName = deal.getDocumentName();
        this.companyId = companyId;
        this.userId = userId;
    }

}
