package pb.wi.kck.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.wi.kck.server.repositories.ProductBlueprintRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductBlueprintRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new ProductBlueprint.ProductBlueprintBuilder()
                    .name("Domestos 1L")
                    .manufacturer("Czyscioszek Smierdzioszek")
                    .description("Swietnie usuwa punkow i inne odpady")
                    .build()));
            log.info("Preloading " + repository.save(new ProductBlueprint.ProductBlueprintBuilder()
                    .name("Dettoll Plyn do podlog")
                    .manufacturer("Dettoll")
                    .build()));
        };
    }


}
