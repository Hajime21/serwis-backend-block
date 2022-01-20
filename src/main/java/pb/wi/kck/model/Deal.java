package pb.wi.kck.model;

import lombok.*;
import org.hibernate.Hibernate;
import pb.wi.kck.dto.DealDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deal_generator")
    @SequenceGenerator(name = "deal_generator", sequenceName = "deal_seq")
    private Integer dealId;
    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private String documentName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deal", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "deal")
    @ToString.Exclude
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deal", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "deal")
    @ToString.Exclude
    private Set<FoodProduct> foodProducts = new LinkedHashSet<>();

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "COMPANY_ID", nullable = false)
//    private Company company;

    public Deal(DealDto dealDto, Company company, User user) {
        this.dealId = dealDto.getDealId();
        this.purchaseDate = dealDto.getPurchaseDate();
        this.purchaseValue = dealDto.getPurchaseValue();
        this.documentName = dealDto.getDocumentName();
        this.user = user;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Deal deal = (Deal) o;
        return dealId != null && Objects.equals(dealId, deal.dealId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
