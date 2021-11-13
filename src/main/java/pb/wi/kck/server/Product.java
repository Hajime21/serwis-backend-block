package pb.wi.kck.server;

import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder(builderMethodName = "productBuilder")
@Entity
public class Product {
    @Id @GeneratedValue protected int productId;
    protected int blueprintId;
    protected int receiptId;
    protected int invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    public Product() {}

    public Product(int productId, int blueprintId, int receiptId, int invoiceId, LocalDate useByDate, int quantity, String location) {
        this.productId = productId;
        this.blueprintId = blueprintId;
        this.receiptId = receiptId;
        this.invoiceId = invoiceId;
        this.useByDate = useByDate;
        this.quantity = quantity;
        this.location = location;
    }
}
