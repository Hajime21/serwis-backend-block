package pb.wi.kck.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "PRODUCT_BLUEPRINT")
@Entity
public class ProductBlueprint {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productBlueprintId;
    private String name;
    private String manufacturer;
    private String barcode;
    private String barcodeType;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "dependedProductBlueprint", cascade = CascadeType.ALL) //, orphanRemoval = true)
    protected List<Product> dependingProducts;

}