package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productId;
    private LocalDate useByDate;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    private ProductBlueprint productBlueprint;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "DEAL_ID", nullable = false)
    private Deal deal;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productId != null && Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
