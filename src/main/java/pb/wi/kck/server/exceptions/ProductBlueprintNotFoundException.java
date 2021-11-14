package pb.wi.kck.server.exceptions;

public class ProductBlueprintNotFoundException extends RuntimeException {
    public ProductBlueprintNotFoundException(Integer blueprintId) {
        super("Could not find product blueprint " + blueprintId);
    }
}
