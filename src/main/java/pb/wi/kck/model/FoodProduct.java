package pb.wi.kck.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@Entity(name = "FoodProduct")
public class FoodProduct extends Product {
    private @Id
    @GeneratedValue
    int foodProductId;

    public FoodProduct() {
    }

    @Builder(builderMethodName = "foodProductBuilder")
    public FoodProduct(int foodProductId, int blueprintId, int receiptId, int invoiceId, LocalDate useByDate, int quantity, String location) {
        super(blueprintId, receiptId, invoiceId, useByDate, quantity, location);
        this.foodProductId = foodProductId;
    }
}
