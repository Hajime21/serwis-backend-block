package pb.wi.kck.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.server.FoodProduct;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, Integer> {

}

