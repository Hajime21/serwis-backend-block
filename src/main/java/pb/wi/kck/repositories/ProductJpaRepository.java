package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageReq);
    Page<Product> findAllByProductBlueprintProductBlueprintNameContaining(String productBlueprint_productBlueprintName, Pageable pageable);

}

