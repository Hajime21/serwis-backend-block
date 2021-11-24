package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.FoodProductClass;

@Repository
public interface FoodProductClassJpaRepository extends JpaRepository<FoodProductClass, Integer>, PagingAndSortingRepository<FoodProductClass, Integer> {
    Page<FoodProductClass> findAll(Pageable pageReq);
}
