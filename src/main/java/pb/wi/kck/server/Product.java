package pb.wi.kck.server;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@SuperBuilder //https://www.baeldung.com/lombok-builder-inheritance
public class Product {
    @Id protected int productId;
    protected int blueprintId;
    protected int receiptId;
    protected int invoiceId;
    protected long useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    public Product() {

    }
}
