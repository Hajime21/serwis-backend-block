package pb.wi.kck.dto;

import lombok.Builder;

import java.time.LocalDateTime;

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

    public ProductBlueprintDTO(int blueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate) {
        this.blueprintId = blueprintId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.barcode = barcode;
        this.barcodeType = barcodeType;
        this.description = description;
        this.imgPath = imgPath;
        this.targetQuantity = targetQuantity;
        this.modificationDate = modificationDate;
    }

    public int getBlueprintId() {
        return blueprintId;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBlueprintDTO that = (ProductBlueprintDTO) o;

        if (blueprintId != that.blueprintId) return false;
        if (targetQuantity != that.targetQuantity) return false;
        if (!name.equals(that.name)) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (barcode != null ? !barcode.equals(that.barcode) : that.barcode != null) return false;
        if (barcodeType != null ? !barcodeType.equals(that.barcodeType) : that.barcodeType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (imgPath != null ? !imgPath.equals(that.imgPath) : that.imgPath != null) return false;
        return modificationDate != null ? modificationDate.equals(that.modificationDate) : that.modificationDate == null;
    }

    @Override
    public int hashCode() {
        int result = blueprintId;
        result = 31 * result + name.hashCode();
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (barcodeType != null ? barcodeType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + targetQuantity;
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductBlueprintDTO{" +
                "blueprintId=" + blueprintId +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", barcode='" + barcode + '\'' +
                ", barcodeType='" + barcodeType + '\'' +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", targetQuantity=" + targetQuantity +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
