package pb.wi.kck.exceptions;

public class FoodProductClassNotFoundException extends RuntimeException{
    public FoodProductClassNotFoundException(Integer foodProductClassId) {
        super("Could not find food product class" + foodProductClassId);
    }
}
