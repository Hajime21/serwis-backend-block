package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

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
@Builder(builderMethodName = "receiptBuilder")
@Entity(name = "Receipt")
public class Receipt {
    private @Id @GeneratedValue @NonNull Integer receiptId;
    private float receiptValue;
    private LocalDate purchaseDate;
    private String shopName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Receipt receipt = (Receipt) o;
        return receiptId != null && Objects.equals(receiptId, receipt.receiptId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
