package pb.wi.kck.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBlueprintRepository extends JpaRepository<ProductBlueprint, Integer> {

}

