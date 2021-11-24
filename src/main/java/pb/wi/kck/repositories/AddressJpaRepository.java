package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Address;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Integer>, PagingAndSortingRepository<Address, Integer> {
    Page<Address> findAll(Pageable pageReq);
}
