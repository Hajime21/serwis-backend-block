package pb.wi.kck.server.exceptions;

public class FoodProductNotFoundException extends RuntimeException {
    public FoodProductNotFoundException(Integer foodProductId) {
        super("Could not find food product " + foodProductId);
    }
}
