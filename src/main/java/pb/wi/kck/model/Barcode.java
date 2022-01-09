package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.BarcodeDto;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BARCODE_ID")
    private Integer barcodeId;

    @Column(name = "CODE", nullable = false, length = 30)
    private String code;

    @Column(name = "TYPE", nullable = false, length = 15)
    private String type;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    private ProductBlueprint productBlueprint;

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "FOOD_PRODUCT_BLUEPRINT_ID", nullable = false)
//    private FoodProductBlueprint foodProductBlueprint;


    public Barcode(BarcodeDto barcodeDto, ProductBlueprint productBlueprint) {
        this.barcodeId = barcodeDto.getBarcodeId();
        this.code = barcodeDto.getCode();
        this.type = barcodeDto.getType();
        this.productBlueprint = productBlueprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Barcode barcode = (Barcode) o;
        return barcodeId != null && Objects.equals(barcodeId, barcode.barcodeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}