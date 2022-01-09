package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.ProductBlueprint;

import java.util.List;

@Repository
public interface ProductBlueprintJpaRepository extends JpaRepository<ProductBlueprint, Integer>, PagingAndSortingRepository<ProductBlueprint, Integer> {
    //@Query("select * from ProductBlueprint u")
    Page<ProductBlueprint> findAll(Pageable pageReq);
    Page<ProductBlueprint> findProductBlueprintByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(String name, Pageable pageReq);
    Page<ProductBlueprint> findProductBlueprintByDescriptionContainingOrderByProductBlueprintNameAsc(String description, Pageable pageReq);
    Page<ProductBlueprint> findProductBlueprintByManufacturerOrderByProductBlueprintNameAsc(String manufacturer, Pageable pageReq);
    ProductBlueprint getById(Integer integer);
}

