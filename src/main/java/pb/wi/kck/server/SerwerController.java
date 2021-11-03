package pb.wi.kck.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SerwerController {
    ConcurrentHashMap<Integer, ProductBlueprint> produkty = new ConcurrentHashMap<>();

    FoodProductBlueprint test1 = FoodProductBlueprint.foodProductBlueprintBuilder()
            .blueprintId(1)
            .name("Mleko UHT 2% 1L")
            .manufacturer("Mlekovita")
            .grammage(1000)
            .build();

    Integer liczbaProduktow = 0;

    //wczytywanie produktow z bazy danych...
    //aktualizacja liczbyProduktow...


    @PostMapping("/api/product/new")
    public Map<String, Integer> postRecipe(@RequestBody ProductBlueprint newProductBlueprint) {
        produkty.put(liczbaProduktow + 1, newProductBlueprint);
        liczbaProduktow++;
        return Map.of("id", liczbaProduktow);
    }

    @GetMapping(value = "/api/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBlueprint getRecipe(@PathVariable Integer id) {
        if(!produkty.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            return produkty.get(id);
        }
    }

}

