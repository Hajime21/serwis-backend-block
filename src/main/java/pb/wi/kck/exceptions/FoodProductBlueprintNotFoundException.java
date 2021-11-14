package pb.wi.kck.exceptions;

public class FoodProductBlueprintNotFoundException extends RuntimeException {
    public FoodProductBlueprintNotFoundException(Integer foodBlueprintId) {
        super("Could not find food product blueprint " + foodBlueprintId);
    }
}
