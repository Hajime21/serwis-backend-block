package pb.wi.kck.server.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "productBlueprintDTOBuilder")
public class ProductBlueprintDTO {
    protected final int blueprintId;
    protected final String name;
    protected final String manufacturer;
    protected final String barcode;
    protected final String barcodeType;
    protected final String description;
    protected final String imgPath;
    protected final int targetQuantity;
    protected final LocalDateTime modificationDate;

}
