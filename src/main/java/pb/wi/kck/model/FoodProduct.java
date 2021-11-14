package pb.wi.kck.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @Data
@AllArgsConstructor
@Entity(name = "foodproduct")
public class FoodProduct extends Product{
    @Id @GeneratedValue private int foodProductId;

    public FoodProduct() {}

    @Builder(builderMethodName = "foodProductBuilder")
    public FoodProduct(int foodProductId, int blueprintId, int receiptId, int invoiceId, LocalDate useByDate, int quantity, String location) {
        super(blueprintId, receiptId, invoiceId, useByDate, quantity, location);
        this.foodProductId = foodProductId;
    }
}
