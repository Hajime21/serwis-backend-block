package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "productBlueprintBuilder")
@Table(name = "PRODUCT_BLUEPRINT")
@Entity(name = "ProductBlueprint")
public class ProductBlueprint {
    //funkcjonalności:
    //1 - essential, do bazy danych
    //2 - apka
    //3 - bajery
    //4 - dieta
    //5 - bajery juz ostre
    protected @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productBlueprintId;                   //1
    protected String name;                        //1
    protected String manufacturer;                //2
    protected String barcode;                     //2
    protected String barcodeType;                 //2
    protected String description;                 //3
    protected String imgPath;                     //3
    protected int targetQuantity;                 //5
    protected LocalDateTime modificationDate;     //5

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "dependedProductBlueprint", cascade = CascadeType.ALL) //, orphanRemoval = true)
    protected List<Product> dependingProducts;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        ProductBlueprint that = (ProductBlueprint) o;
//        return productBlueprintId != null && Objects.equals(productBlueprintId, that.productBlueprintId);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }

}