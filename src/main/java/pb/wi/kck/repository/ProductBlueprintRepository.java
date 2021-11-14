package pb.wi.kck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.ProductBlueprint;

@Repository
public interface ProductBlueprintRepository extends JpaRepository<ProductBlueprint, Integer> {}

