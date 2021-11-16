package pb.wi.kck.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.repositories.ProductBlueprintJpaRepository;
import pb.wi.kck.repositories.ProductJpaRepository;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductBlueprintJpaRepository repository1, ProductJpaRepository repository2) {
        return args -> {
            var pb = new ProductBlueprint(1,"Domestos 1L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(2,"Domestos 2L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(3,"Domestos 3L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(4,"Domestos 4L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(5,"Domestos 5L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(6,"Domestos 6L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(7,"Domestos 7L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(8,"Domestos 8L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(9,"Domestos 9L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(10,"Domestos 10L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(11,"Domestos 11L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));
            pb = new ProductBlueprint(12,"Domestos 12L","Clean","","","Usuwa kase","",1, LocalDateTime.now(), null);
            log.info("Preloading " + repository1.save(pb));


            //var p = new Product(1, 0, 0, LocalDate.now(), 1, "polka b", pb);
            //log.info("Preloading " + repository2.save(p));
            /*log.info("Preloading " + repository.save(new ProductBlueprint.ProductBlueprintBuilder()
                    .name("Dettoll Plyn do podlog")
                    .manufacturer("Dettoll")
                    .build()));*/
        };
    }


}
