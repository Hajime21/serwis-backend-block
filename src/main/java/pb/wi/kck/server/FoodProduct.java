package pb.wi.kck.server;


import java.util.concurrent.ConcurrentHashMap;


public class FoodProduct extends Product{
    private int grammage;
    private String useByDate;
    private String productPackage;
    private int[] microElements;

    FoodProduct(String name, String description, String barcode, String imgPath, int targetQuantity) {
        super(name, description, barcode, imgPath, targetQuantity);
    }
}
