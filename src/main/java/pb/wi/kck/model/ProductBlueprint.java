package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.ProductBlueprintDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductBlueprint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_blueprint_generator")
    @SequenceGenerator(name = "product_blueprint_generator", sequenceName = "product_blueprint_seq", initialValue = 50)
    private Integer productBlueprintId;
    private String productBlueprintName;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productBlueprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productBlueprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Barcode> barcodes = new LinkedHashSet<>();

    public ProductBlueprint(ProductBlueprintDto productBlueprintDto) {
        this.productBlueprintId = productBlueprintDto.getProductBlueprintId();
        this.productBlueprintName = productBlueprintDto.getProductBlueprintName();
        this.manufacturer = productBlueprintDto.getManufacturer();
        this.description = productBlueprintDto.getDescription();
        this.imgPath = productBlueprintDto.getImgPath();
        this.modificationDate = productBlueprintDto.getModificationDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductBlueprint that = (ProductBlueprint) o;
        return productBlueprintId != null && Objects.equals(productBlueprintId, that.productBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}