package pb.wi.kck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.FoodProduct;

@Repository
public interface FoodProductJpaRepository extends JpaRepository<FoodProduct, Integer> {

}

