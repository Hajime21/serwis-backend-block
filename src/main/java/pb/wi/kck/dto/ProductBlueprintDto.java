package pb.wi.kck.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

//@NoArgsConstructor
//@AllArgsConstructor
@Value //@Data
@Builder
public class ProductBlueprintDto implements Serializable {
    Integer productBlueprintId;
    String productBlueprintName;
    String manufacturer;
    String barcode;
    String barcodeType;
    String description;
    String imgPath;
    LocalDateTime modificationDate;
}
