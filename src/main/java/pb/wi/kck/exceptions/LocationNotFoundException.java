package pb.wi.kck.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Integer locationId) {
        super("Could not find location " + locationId);
    }
}
