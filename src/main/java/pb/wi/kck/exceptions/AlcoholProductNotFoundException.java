package pb.wi.kck.exceptions;

public class AlcoholProductNotFoundException extends RuntimeException {
    public AlcoholProductNotFoundException(Integer alcoholProductId) {
        super("Could not find alcohol product " + alcoholProductId);
    }
}
