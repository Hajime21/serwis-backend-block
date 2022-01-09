package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Barcode;

@Repository
public interface BarcodeJpaRepository extends JpaRepository<Barcode, Integer>, PagingAndSortingRepository<Barcode, Integer> {
    Page<Barcode> findAll(Pageable pageReq);
    Page<Barcode> findAllByCodeContaining(String code, Pageable pageReq);
}