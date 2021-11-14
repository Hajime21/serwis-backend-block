package pb.wi.kck.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.repository.ProductBlueprintRepository;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductBlueprintRepository repository) {
        return args -> {
            var prod = new ProductBlueprint(1,"Domestos 1L","Clean","","","Usuwa kase","",1, LocalDateTime.now());
            log.info("Preloading " + repository.save(prod));
            /*log.info("Preloading " + repository.save(new ProductBlueprint.ProductBlueprintBuilder()
                    .name("Dettoll Plyn do podlog")
                    .manufacturer("Dettoll")
                    .build()));*/
        };
    }


}
