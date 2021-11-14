package pb.wi.kck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.FoodProductBlueprint;

@Repository
public interface FoodProductBlueprintJpaRepository extends JpaRepository<FoodProductBlueprint, Integer> {}

