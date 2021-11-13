package pb.wi.kck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import pb.wi.kck.server.FoodProduct;
import pb.wi.kck.server.FoodProductBlueprint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SerwerApplication {

    public long createTimestamp() {
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.now().atZone(zoneId).toEpochSecond();
    }

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);

        FoodProductBlueprint test1 = FoodProductBlueprint.foodProductBlueprintBuilder()
                .blueprintId(1)
                .name("Mleko UHT 2% 1L")
                .manufacturer("Mlekovita")
                .grammage(1000)
                .build();

        FoodProduct test2 = FoodProduct.foodProductBuilder()
                .productId(1)
                .blueprintId(1)
                .receiptId(10)
                .location("Półka B2")
                .build();

        System.out.println("\n\n");
        System.out.println(test1);
        System.out.println(test1.getName());
        System.out.println("\n");
        System.out.println(test2);
        System.out.println(test2.getLocation());

    }

}