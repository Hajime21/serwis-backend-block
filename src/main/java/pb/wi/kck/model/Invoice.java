package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "invoiceBuilder")
@Entity(name = "Invoice")
public class Invoice {
    private @Id @GeneratedValue @NonNull Integer invoiceId;
    private String invoiceName;
    private int sellerNIP;
    private int buyerNIP;
    private float invoiceValue;
    private long purchaseDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId != null && Objects.equals(invoiceId, invoice.invoiceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
