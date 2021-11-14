package pb.wi.kck.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.server.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

