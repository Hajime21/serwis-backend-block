package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "companyBuilder")
@Entity
public class Company {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer companyId;
    private String companyName;
    private String companyNIP;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return companyId != null && Objects.equals(companyId, company.companyId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
