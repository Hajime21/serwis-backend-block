package pb.wi.kck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.ProductBlueprint;

@Repository
public interface ProductBlueprintJpaRepository extends JpaRepository<ProductBlueprint, Integer> {}

