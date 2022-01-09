package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.FoodProductBlueprint;

@Repository
public interface FoodProductBlueprintJpaRepository extends JpaRepository<FoodProductBlueprint, Integer>, PagingAndSortingRepository<FoodProductBlueprint, Integer> {
    Page<FoodProductBlueprint> findAll(Pageable pageReq);
    Page<FoodProductBlueprint> findAllByNameContainingOrDescriptionContainingOrManufacturerContainingOrderByName(String name, String description, String manufacturer, Pageable pageable);
}
