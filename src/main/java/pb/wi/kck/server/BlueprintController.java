package pb.wi.kck.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/blueprint")
public class BlueprintController {

    ConcurrentHashMap<Integer, ProductBlueprint> blueprints = new ConcurrentHashMap<>();
    Integer liczbaBlueprintow = 0;

    public BlueprintController() {
        var temp = ProductBlueprint.productBlueprintBuilder()
                .blueprintId(1)
                .name("Miami Hopki")
                .barcode("5907437365489")
                .barcodeType("EAN-13")
                .manufacturer("Bruggen Polska Sp. z o. o.")
                .description("Kuleczki zbożowe o smaku czekoladowym")
                .modificationDate(LocalDateTime.now())
                .targetQuantity(5)
                .build();
        blueprints.put(1, temp);
    }

    //wczytywanie produktow z bazy danych...
    //aktualizacja liczbyProduktow...


    @PostMapping("/new")
    public Map<String, Integer> postProductBlueprint(@RequestBody ProductBlueprint newProductBlueprint) {
        blueprints.put(liczbaBlueprintow + 1, newProductBlueprint);
        liczbaBlueprintow++;
        return Map.of("blueprintId", liczbaBlueprintow);
    }

    @GetMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBlueprint getProductBlueprint(@PathVariable Integer blueprintId) {
        if (!blueprints.containsKey(blueprintId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma blueprinta produktu o podanym ID.");
        } else {
            return blueprints.get(blueprintId);
        }
    }

    @DeleteMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBlueprint deleteProductBlueprint(@PathVariable Integer blueprintId) {
        if (!blueprints.containsKey(blueprintId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma blueprinta produktu o podanym ID.");
        } else {
            ProductBlueprint temp = blueprints.get(blueprintId);
            blueprints.remove(blueprintId);
            return temp;
        }
    }

    @PutMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBlueprint modifyProductBlueprint(@PathVariable Integer blueprintId, @RequestBody ProductBlueprint modifiedProductBlueprint) {
        if (!blueprints.containsKey(blueprintId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie ma blueprinta produktu o podanym ID.");
        } else {
            blueprints.put(blueprintId, modifiedProductBlueprint);
            return blueprints.get(blueprintId);
        }
    }

}

