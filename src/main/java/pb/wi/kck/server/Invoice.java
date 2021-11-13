package pb.wi.kck.server;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Invoice {
    @Id private int invoiceId;
    private String invoiceName;
    private int sellerNIP;
    private int buyerNIP;
    private float invoiceValue;
    private long dataWystawienia;
}
