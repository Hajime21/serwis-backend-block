package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductBlueprint {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer productBlueprintId;
    private String productBlueprintName;
    private String manufacturer;
    private String description;
    private String imgPath;
    private LocalDateTime modificationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductBlueprint that = (ProductBlueprint) o;
        return productBlueprintId != null && Objects.equals(productBlueprintId, that.productBlueprintId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}