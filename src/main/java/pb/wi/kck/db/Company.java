package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer companyId;
    private String companyName;
    private String companyNIP;
    //private Address companyAddress; //RELACJE

}
