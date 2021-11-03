package pb.wi.kck.server;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Product {
    @Id private int productId;
    private int blueprintId;
    private int receiptId;
    private LocalDate termin;
    private long terminL;


}
