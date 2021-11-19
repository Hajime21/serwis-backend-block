package pb.wi.kck.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productId;
    private Integer receiptId;
    private Integer invoiceId;
    private LocalDate useByDate;
    private int quantity; //czy na pewno?
    private String location;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)//, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_BLUEPRINT_ID", nullable = false)
    protected ProductBlueprint dependedProductBlueprint;

}
