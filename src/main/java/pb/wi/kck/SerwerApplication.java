package pb.wi.kck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pb.wi.kck.server.FoodProduct;
import pb.wi.kck.server.FoodProductBlueprint;

import java.time.LocalDate;

@SpringBootApplication
public class SerwerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);

        FoodProductBlueprint test1 = FoodProductBlueprint.foodProductBlueprintBuilder()
                .blueprintId(1)
                .name("Mleko UHT 2% 1L")
                .manufacturer("Mlekovita")
                .grammage(1000)
                .build();

        FoodProduct test2 = FoodProduct.builder()
                .productId(1)
                .blueprintId(1)
                .receiptId(10)
                .build();

    }

}