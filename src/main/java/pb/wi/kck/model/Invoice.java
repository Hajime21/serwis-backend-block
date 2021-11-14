package pb.wi.kck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Invoice {
    @Id private Integer invoiceId;
    private String invoiceName;
    private int sellerNIP;
    private int buyerNIP;
    private float invoiceValue;
    private long dataWystawienia;
}
