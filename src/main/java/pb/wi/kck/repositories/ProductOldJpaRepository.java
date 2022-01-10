package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.ProductOld;

@Repository
public interface ProductOldJpaRepository extends JpaRepository<ProductOld, Integer>, PagingAndSortingRepository<ProductOld, Integer> {
    Page<ProductOld> findAll(Pageable pageReq);
    Page<ProductOld> findAllByProductBlueprintWithBarcode_ProductBlueprintNameOrderByProductBlueprintWithBarcode_ProductBlueprintName(String productBlueprint_productBlueprintName, Pageable pageable);

}

