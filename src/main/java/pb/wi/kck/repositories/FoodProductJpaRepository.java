package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.FoodProduct;
import pb.wi.kck.model.FoodProductBlueprint;

@Repository
public interface FoodProductJpaRepository extends JpaRepository<FoodProduct, Integer>, PagingAndSortingRepository<FoodProduct, Integer> {
    Page<FoodProduct> findAll(Pageable pageReq);
    Page<FoodProduct> findAllByFoodProductBlueprintName(String foodProductBlueprint_name, Pageable pageable);
}
