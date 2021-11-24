package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.AlcoholProduct;

@Repository
public interface AlcoholProductJpaRepository extends JpaRepository<AlcoholProduct, Integer>, PagingAndSortingRepository<AlcoholProduct, Integer> {
    Page<AlcoholProduct> findAll(Pageable pageReq);
}
