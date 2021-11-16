package pb.wi.kck.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "productBuilder")
@Table(name = "PRODUCT")
@Entity(name = "Product")
public class Product {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer productId;
    protected Integer receiptId;
    protected Integer invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)//, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    @JsonBackReference
    protected ProductBlueprint dependedProductBlueprint;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Product product = (Product) o;
//        return productId != null && Objects.equals(productId, product.productId);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }

}
