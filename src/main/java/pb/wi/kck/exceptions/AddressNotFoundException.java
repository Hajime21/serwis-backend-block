package pb.wi.kck.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer addressId) {
        super("Could not find address " + addressId);
    }
}
