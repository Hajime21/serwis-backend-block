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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "barcode_generator")
    @SequenceGenerator(name = "barcode_generator", sequenceName = "barcode_seq")
    @Column(name = "BARCODE_ID")
    private Integer barcodeId;

    @Column(name = "CODE", nullable = false, length = 30)
    private String code;

    @Column(name = "TYPE", nullable = false, length = 15)
    private String type;

    //TODO: barcode tylko na foodProductBlueprinty zrobic po BD, dodac optional=false
    //TODO: nie wiem czy dobra kaskada :/
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    private ProductBlueprint productBlueprint;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FOOD_PRODUCT_BLUEPRINT_ID")
    private FoodProductBlueprint foodProductBlueprint;

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "FOOD_PRODUCT_BLUEPRINT_ID", nullable = false)
//    private FoodProductBlueprint foodProductBlueprint;


    public Barcode(BarcodeDto barcodeDto, ProductBlueprint productBlueprint) {
        this.barcodeId = barcodeDto.getBarcodeId();
        this.code = barcodeDto.getCode();
        this.type = barcodeDto.getType();
        this.productBlueprint = productBlueprint;
        this.foodProductBlueprint = null;
    }

    public Barcode(BarcodeDto barcodeDto, FoodProductBlueprint foodProductBlueprint) {
        this.barcodeId = barcodeDto.getBarcodeId();
        this.code = barcodeDto.getCode();
        this.type = barcodeDto.getType();
        this.productBlueprint = null;
        this.foodProductBlueprint = foodProductBlueprint;
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