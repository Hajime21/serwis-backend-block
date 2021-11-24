package pb.wi.kck.exceptions;

public class DealNotFoundException extends RuntimeException {
    public DealNotFoundException(Integer dealId) {
        super("Could not find deal " + dealId);
    }
}
