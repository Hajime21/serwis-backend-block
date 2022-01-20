package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductOldDto;

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
public class ProductOld {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_old_generator")
    @SequenceGenerator(name = "product_old_generator", sequenceName = "product_old_seq")
    private Integer productId;
    private LocalDate useByDate;
    private int quantity;
    private String location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_WITH_BARCODE_ID", nullable = false)
    private ProductBlueprintWithBarcode productBlueprintWithBarcode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEAL_ID", nullable = false)
    private Deal deal;

    public ProductOld(ProductOldDto productOldDto, ProductBlueprintWithBarcode productBlueprintWithBarcode, Deal deal) {
        this.productId = productOldDto.getProductId();
        this.useByDate = productOldDto.getUseByDate();
        this.quantity = productOldDto.getQuantity();
        this.location = productOldDto.getLocation();
        this.productBlueprintWithBarcode = productBlueprintWithBarcode;
        this.deal = deal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductOld product = (ProductOld) o;
        return productId != null && Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
