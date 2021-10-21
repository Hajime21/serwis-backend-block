package pb.wi.kck.server;

public class Product {
    private int id;
    private String name;
    private String description;
    private String barcode;
    private String imgPath;
    private int targetQuantity;

    Product(String name, String description, String barcode, String imgPath, int targetQuantity) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.imgPath = imgPath;
        this.targetQuantity = targetQuantity;
    }
}