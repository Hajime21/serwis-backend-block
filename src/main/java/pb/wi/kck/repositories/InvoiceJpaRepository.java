package pb.wi.kck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.Invoice;
import pb.wi.kck.model.Receipt;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<Invoice, Integer> {}

