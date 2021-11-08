package pb.wi.kck.server;

import javax.persistence.Entity;

@Entity
public class Invoice {
    private int invoiceId;
    private String invoiceName;
    private int sellerNIP;
    private int buyerNIP;
    private float invoiceValue;
    private long dataWystawienia;
}
