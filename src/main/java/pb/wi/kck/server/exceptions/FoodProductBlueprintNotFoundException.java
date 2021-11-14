package pb.wi.kck.server.exceptions;

public class FoodProductBlueprintNotFoundException extends RuntimeException {
    public FoodProductBlueprintNotFoundException(Integer foodBlueprintId) {
        super("Could not find food product blueprint " + foodBlueprintId);
    }
}
