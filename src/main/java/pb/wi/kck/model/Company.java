package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.CompanyDto;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
    @SequenceGenerator(name = "company_generator", sequenceName = "company_seq")
    private Integer companyId;
    private String companyName;
    private String companyNIP;

    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @ToString.Exclude
    private Set<Deal> deals = new LinkedHashSet<>();

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "ADDRESS_ID", nullable = false)
//    private Address address;

    public Company(CompanyDto companyDto, Address address) {
        this.companyId = companyDto.getCompanyId();
        this.companyName = companyDto.getCompanyName();
        this.companyNIP = companyDto.getCompanyNIP();
        this.address = address;
    }


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
