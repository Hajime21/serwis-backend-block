package pb.wi.kck.server;

import lombok.*;

import javax.persistence.Entity;

@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @Data
@AllArgsConstructor
@Entity
public class FoodProduct extends Product{

    @Builder(builderMethodName = "foodProductBuilder")
    public FoodProduct(int productId, int blueprintId, int receiptId, int invoiceId, long useByDate, int quantity, String location) {
        super(productId, blueprintId, receiptId, invoiceId, useByDate, quantity, location);
    }
}
