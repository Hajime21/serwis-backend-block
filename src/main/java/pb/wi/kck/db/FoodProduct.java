package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "FoodProduct")
public class FoodProduct {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer foodProductId;
    protected Integer blueprintId;
    protected Integer receiptId;
    protected Integer invoiceId;
    protected LocalDate useByDate;
    protected int quantity; //czy na pewno?
    protected String location;

}
