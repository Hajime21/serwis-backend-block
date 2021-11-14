package pb.wi.kck.server;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "productBuilder")
@Entity(name = "Product")
public class Product {
    private @Id @GeneratedValue int productId;
    protected int blueprintId;
    protected int receiptId;
    protected int invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    public Product() {}

    //konstruktor dla dziedziczacych klas (bez productId)
    public Product(int blueprintId, int receiptId, int invoiceId, LocalDate useByDate, int quantity, String location) {
        this.blueprintId = blueprintId;
        this.receiptId = receiptId;
        this.invoiceId = invoiceId;
        this.useByDate = useByDate;
        this.quantity = quantity;
        this.location = location;
    }
}
