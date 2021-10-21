package pb.wi.kck.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SerwerController {
    ConcurrentHashMap<Integer, Product> produkty = new ConcurrentHashMap<>();

    Integer liczbaProduktow = 0;

    @PostMapping("/api/product/new")
    public Map<String, Integer> postRecipe(@RequestBody Product newProduct) {
        produkty.put(liczbaProduktow + 1, newProduct);
        liczbaProduktow++;
        return Map.of("id", liczbaProduktow);
    }

    @GetMapping(value = "/api/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getRecipe(@PathVariable Integer id) {
        if(!produkty.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            return produkty.get(id);
        }
    }

}

