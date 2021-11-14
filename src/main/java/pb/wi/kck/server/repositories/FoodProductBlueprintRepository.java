package pb.wi.kck.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.server.FoodProductBlueprint;

@Repository
public interface FoodProductBlueprintRepository extends JpaRepository<FoodProductBlueprint, Integer> {

}

