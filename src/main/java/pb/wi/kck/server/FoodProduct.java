package pb.wi.kck.server;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @Data
@AllArgsConstructor
@Entity
public class FoodProduct extends Product{

    @Builder(builderMethodName = "foodProductBuilder")
    public FoodProduct(int productId, int blueprintId, int receiptId, int invoiceId, LocalDate useByDate, int quantity, String location) {
        super(productId, blueprintId, receiptId, invoiceId, useByDate, quantity, location);
    }
}
