package pb.wi.kck.server;

import lombok.*;
import org.hibernate.bytecode.internal.javassist.BulkAccessor;

import javax.persistence.Id;

@Data
@Builder
public class ProductBlueprint {
    //funkcjonalności:
    //1 - essential, do bazy danych
    //2 - apka
    //3 - bajery
    //4 - dieta
    //5 - bajery juz ostre
    @NonNull @Id private int blueprintId;       //1
    @NonNull private String name;               //1
    private String barcode;                     //2
    private String barcodeType;                 //2
    private String description;                 //3
    private String imgPath;                     //3
    private int targetQuantity;                 //3

    public static class Builder {
        private ProductBlueprint productBlueprint = new ProductBlueprint();

        public Builder blueprintId(int val) { productBlueprint.blueprintId = val; return this; }
        public Builder name(String val) { productBlueprint.name = val; return this; }
        public Builder barcode(String val) { productBlueprint.barcode = val; return this; }
        public Builder barcodeType(String val) { productBlueprint.barcodeType = val; return this; }
        public Builder description(String val) { productBlueprint.description = val; return this; }
        public Builder imgPath(String val) { productBlueprint.imgPath = val; return this; }
        public Builder targetQuantity(int val) { productBlueprint.targetQuantity = val; return this; }
        public ProductBlueprint build() { return productBlueprint; }
    }

    protected ProductBlueprint() {}

    protected ProductBlueprint(Builder builder) {
        this.blueprintId = builder.blueprintId;
        this.name = builder.name;
        this.barcode = builder.barcode;
        this.barcodeType = builder.barcodeType;
        this.description = builder.description;
        this.imgPath = builder.imgPath;
        this.targetQuantity = builder.targetQuantity;
    }


}