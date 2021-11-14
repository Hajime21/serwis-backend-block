package pb.wi.kck.server.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super("Could not find product " + productId);
    }
}
