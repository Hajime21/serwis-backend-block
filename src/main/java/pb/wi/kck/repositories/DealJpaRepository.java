package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Deal;

@Repository
public interface DealJpaRepository extends JpaRepository<Deal, Integer>, PagingAndSortingRepository<Deal, Integer> {
    Page<Deal> findAll(Pageable pageReq);
}