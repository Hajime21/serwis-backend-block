package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "foodProductBuilder")
@Entity(name = "FoodProduct")
public class FoodProduct {
    private @Id @GeneratedValue @NonNull Integer foodProductId;
    protected Integer blueprintId;
    protected Integer receiptId;
    protected Integer invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FoodProduct that = (FoodProduct) o;
        return foodProductId != null && Objects.equals(foodProductId, that.foodProductId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
