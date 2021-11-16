package pb.wi.kck.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.wi.kck.model.Product;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.repositories.ProductBlueprintJpaRepository;
import pb.wi.kck.repositories.ProductJpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductBlueprintJpaRepository repository1, ProductJpaRepository repository2) {
        return args -> {
            var pb = new ProductBlueprint(1,"Domestos 1L","UNILEVER","8717163350034","EAN-13","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(2,"4Move Vitamin Water","FOODCARE","5900552077756","EAN-13","Woda smakowa","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(3,"Chappi z drobiem","MARS PC","5900951252815","PDF417","Karma pełnoporcjowa","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(4,"Coca-Cola","Coca-Cola Company","5000112529043","GTIN-13","Pyszny trunek","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(5,"MYDŁO/PŁ SILK POMPKA 250ML","DOVE","8717163605776","EAN-13","Mydlo do mycia rąk","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(6,"SEREK KREMOWY SZPINAK 150G","HOCHLAND","5902899139470","EAN-13","Serek sniadaniowy","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(7,"Woda Mineralna","NESTLE WAT","5900635000015","EAN-13","Woda pitna","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(8,"KISIEL","FOODCARE","5900552012238","EAN-13","Kisielek","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(9,"TAFT LAKIER/WŁ","HENKEL&SCH","4012800706002","PDF417","Lakier do wlosow","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(10,"NESTLE PŁATKI","PACIFIC","5900020004659","Data Matrix","Platki sniadaniowe","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(11,"BOBOVITA Zupa Dyniowa","NUTRICIA","5900852033766","UPC","Zupa z dyni","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(12,"7UP","PEPSICO","5900497340502","EAN-13","Napoj gazowany","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));

            var p = new Product(1, 0, 0, LocalDate.of(2023, 2, 18), 1, "Półka B", repository1.getById(4));
            log.info("Preloading "+ repository2.save(p));
            p = new Product(2, 0, 0, LocalDate.of(2023, 5, 8), 10, "Półka B", repository1.getById(2));
            log.info("Preloading "+ repository2.save(p));
            p = new Product(3, 0, 0, LocalDate.of(2022, 3, 16), 3, "Półka A", repository1.getById(11));
            log.info("Preloading "+ repository2.save(p));
            p = new Product(4, 0, 0, LocalDate.of(2027, 1, 12), 12, "Półka B", repository1.getById(7));
            log.info("Preloading "+ repository2.save(p));
            p = new Product(5, 0, 0, LocalDate.of(2024, 6, 3), 5, "Półka C", repository1.getById(8));
            log.info("Preloading "+ repository2.save(p));

            /*
            var p = new Product(1, 0, 0, LocalDate.now(), 100, "polka b", pb);
            log.info("Preloading " + repository2.save(p));
            log.info("Preloading " + repository2.save(new ProductBlueprint.ProductBlueprintBuilder()
                    .name("Dettoll Plyn do podlog")
                    .manufacturer("Dettoll")
                    .build()));

             */
        };
    }


}
