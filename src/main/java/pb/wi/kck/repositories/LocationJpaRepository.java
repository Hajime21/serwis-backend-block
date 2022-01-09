package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Integer>, PagingAndSortingRepository<Location, Integer> {
    Page<Location> findAll(Pageable pageReq);
    Page<Location> findAllByLocationNameContaining(String name, Pageable pageReq);
}
