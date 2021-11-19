package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class FoodProductClass {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductClassId;
    private String foodProductClassName;
    private int desiredQuantity;

}
