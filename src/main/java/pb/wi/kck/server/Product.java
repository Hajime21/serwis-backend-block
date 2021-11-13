package pb.wi.kck.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@Builder(builderMethodName = "productBuilder")
public class Product {
    @Id protected int productId;
    protected int blueprintId;
    protected int receiptId;
    protected int invoiceId;
    protected long useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    public Product() {}

}
