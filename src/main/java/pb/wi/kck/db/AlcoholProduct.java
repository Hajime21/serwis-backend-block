package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlcoholProduct {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer alcoholProductId;
    private Integer foodProductBlueprintId;
    private Integer alcoholTypeId;
    private Integer transactionId;
    private int quantity;
    private String location;
    private float voltage;


}
