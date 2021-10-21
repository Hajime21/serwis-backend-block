package pb.wi.kck.server;

public class Product {
    private String name;
    private String description;
    private String ingredients;
    private String directions;

    Product(String name, String description, String ingredients, String directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}