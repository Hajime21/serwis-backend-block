package pb.wi.kck.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pb.wi.kck.model.Product;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    ConcurrentHashMap<Integer, Product> products = new ConcurrentHashMap<>();
    Integer liczbaProduktow = 0;

    //wczytywanie produktow z bazy danych...
    //aktualizacja liczbyProduktow...


    @PostMapping("/new")
    public Map<String, Integer> postProduct(@RequestBody Product newProduct) {
        products.put(liczbaProduktow + 1, newProduct);
        liczbaProduktow++;
        return Map.of("productId", liczbaProduktow);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable Integer productId) {
        if(!products.containsKey(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            return products.get(productId);
        }
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product deleteProduct(@PathVariable Integer productId) {
        if(!products.containsKey(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            Product temp = products.get(productId);
            products.remove(productId);
            return temp;
        }
    }

    @PutMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product modifyProduct(@PathVariable Integer productId, @RequestBody Product modifiedProduct) {
        if(!products.containsKey(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma produktu o podanym ID.");
        } else {
            products.put(productId, modifiedProduct);
            return products.get(productId);
        }
    }

}

