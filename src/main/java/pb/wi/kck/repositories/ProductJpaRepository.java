package pb.wi.kck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

}

