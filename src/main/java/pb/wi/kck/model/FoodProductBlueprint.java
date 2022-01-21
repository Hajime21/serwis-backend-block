package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.FoodProductBlueprintDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FoodProductBlueprint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_product_blueprint_generator")
    @SequenceGenerator(name = "food_product_blueprint_generator", sequenceName = "food_product_blueprint_seq", initialValue = 50)
    private Integer foodProductBlueprintId;
    private String name;
    private String manufacturer;
    //private String barcode;
    //private String barcodeType;
    private String description;
    //private String ingredients;
    private String imgPath;
    private LocalDateTime modificationDate;
    private int measuredValue;
    private int kcalPer100;
    private int protein;
    private int fat;
    private int carbohydrates;

    //TODO: nie wiem czy dobre kaskady ¯\_(ツ)_/¯
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodProductBlueprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<FoodProduct> foodProducts = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodProductBlueprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Barcode> barcodes = new LinkedHashSet<>();

    public FoodProductBlueprint(FoodProductBlueprintDto foodProductBlueprintDto) {
        this.foodProductBlueprintId = foodProductBlueprintDto.getFoodProductBlueprintId();
        this.name = foodProductBlueprintDto.getName();
        this.manufacturer = foodProductBlueprintDto.getManufacturer();
        this.description = foodProductBlueprintDto.getDescription();
        this.imgPath = foodProductBlueprintDto.getImgPath();
        this.modificationDate = foodProductBlueprintDto.getModificationDate();
        this.measuredValue = foodProductBlueprintDto.getMeasuredValue();
        this.kcalPer100 = foodProductBlueprintDto.getKcalPer100();
        this.protein = foodProductBlueprintDto.getProtein();
        this.fat = foodProductBlueprintDto.getFat();
        this.carbohydrates = foodProductBlueprintDto.getCarbohydrates();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FoodProductBlueprint that = (FoodProductBlueprint) o;
        return foodProductBlueprintId != null && Objects.equals(foodProductBlueprintId, that.foodProductBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
