package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductDto;

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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_seq", initialValue = 50)
    private Integer productId;
    private LocalDate useByDate;
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    private ProductBlueprint productBlueprint;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEAL_ID", nullable = false)
    private Deal deal;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    public Product(ProductDto productDto, ProductBlueprint productBlueprint, Deal deal, Location location) {
        this.productId = productDto.getProductId();
        this.useByDate = productDto.getUseByDate();
        this.quantity = productDto.getQuantity();
        this.productBlueprint = productBlueprint;
        this.deal = deal;
        this.location = location;
    }

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
