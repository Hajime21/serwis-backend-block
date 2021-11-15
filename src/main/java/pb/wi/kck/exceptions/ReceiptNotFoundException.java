package pb.wi.kck.exceptions;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(Integer receiptId) {
        super("Could not find receipt " + receiptId);
    }
}
