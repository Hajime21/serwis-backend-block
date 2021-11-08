package pb.wi.kck.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class BlueprintController {

    ConcurrentHashMap<Integer, ProductBlueprint> blueprinty = new ConcurrentHashMap<>();
    Integer liczbaBlueprintow = 0;

    //wczytywanie produktow z bazy danych...
    //aktualizacja liczbyProduktow...


    @PostMapping("/api/product/new")
    public Map<String, Integer> postRecipe(@RequestBody ProductBlueprint newProductBlueprint) {
        blueprinty.put(liczbaBlueprintow + 1, newProductBlueprint);
        liczbaBlueprintow++;
        return Map.of("id", liczbaBlueprintow);
    }

    @GetMapping(value = "/api/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBlueprint getRecipe(@PathVariable Integer id) {
        if(!blueprinty.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            return blueprinty.get(id);
        }
    }

}

