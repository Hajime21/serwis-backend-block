package pb.wi.kck.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super("Could not find user " + userId);
    }
}
