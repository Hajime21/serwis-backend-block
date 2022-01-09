package pb.wi.kck.model;

import lombok.*;
import pb.wi.kck.dto.BarcodeDto;

import javax.persistence.*;

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
}