package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer transactionId;
    private Integer companyId;
    private Integer userId;
    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private String documentName;

}
