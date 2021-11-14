package pb.wi.kck.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.server.ProductBlueprint;

@Repository
public interface ProductBlueprintRepository extends JpaRepository<ProductBlueprint, Integer> {

}

