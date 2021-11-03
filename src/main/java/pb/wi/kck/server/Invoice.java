package pb.wi.kck.server;

import lombok.NonNull;

public class Invoice {
    @NonNull private int invoiceId;
    private String invoiceName;
    @NonNull private int sellerNIP;
    @NonNull private int buyerNIP;
    @NonNull private float invoiceValue;
    @NonNull private long dataWystawienia;
}
