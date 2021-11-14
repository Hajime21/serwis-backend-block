package pb.wi.kck.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Entity
public class ProductBlueprint {
    //funkcjonalności:
    //1 - essential, do bazy danych
    //2 - apka
    //3 - bajery
    //4 - dieta
    //5 - bajery juz ostre
    protected @Id @GeneratedValue int blueprintId;//1
    protected String name;                        //1
    protected String manufacturer;                //2
    protected String barcode;                     //2
    protected String barcodeType;                 //2
    protected String description;                 //3
    protected String imgPath;                     //3
    protected int targetQuantity;                 //5
    protected LocalDateTime modificationDate;     //5

    public ProductBlueprint() {}

    public ProductBlueprint(int blueprintId, String name, String manufacturer, String barcode, String barcodeType, String description, String imgPath, int targetQuantity, LocalDateTime modificationDate) {
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

    public void setBlueprintId(int blueprintId) {
        this.blueprintId = blueprintId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        this.barcodeType = barcodeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    public void setTargetQuantity(int targetQuantity) {
        this.targetQuantity = targetQuantity;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBlueprint that = (ProductBlueprint) o;

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
        return "ProductBlueprint{" +
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
