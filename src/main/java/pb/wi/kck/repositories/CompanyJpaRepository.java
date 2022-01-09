package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Company;

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Integer>, PagingAndSortingRepository<Company, Integer> {
    Page<Company> findAll(Pageable pageReq);
    Page<Company> findAllByCompanyNIPContainingOrCompanyNameContaining(String companyNIP, String companyName, Pageable pageReq);
}