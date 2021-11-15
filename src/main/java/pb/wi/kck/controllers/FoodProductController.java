package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.FoodProduct;
import pb.wi.kck.repositories.FoodProductJpaRepository;
import pb.wi.kck.server.exceptions.FoodProductNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/products/food")
public class FoodProductController {

    private final FoodProductJpaRepository foodProductJpaRepository;

    FoodProductController(FoodProductJpaRepository foodProductJpaRepository) {
        this.foodProductJpaRepository = foodProductJpaRepository;
    }

    @GetMapping()
    List<FoodProduct> getAll() {
        return foodProductJpaRepository.findAll();
    }

    @PostMapping("/new")
    FoodProduct newFoodProduct(@RequestBody FoodProduct newFoodProduct) {
        return foodProductJpaRepository.save(newFoodProduct);
    }

    @GetMapping(value = "/{foodProductId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProduct getFoodProduct(@PathVariable Integer foodProductId) {
        return foodProductJpaRepository.findById(foodProductId)
                .orElseThrow(() -> new FoodProductNotFoundException(foodProductId));
    }

    @DeleteMapping(value = "/{foodProductId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteFoodProduct(@PathVariable Integer foodProductId) {
        foodProductJpaRepository.deleteById(foodProductId);
    }

    @PutMapping(value = "/{foodProductId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProduct modifyFoodProduct(@PathVariable Integer foodProductId, @RequestBody FoodProduct modifiedFoodProduct) {
        return foodProductJpaRepository.findById(foodProductId)
                .map(fp -> {
                    fp.setFoodProductId(modifiedFoodProduct.getFoodProductId());
                    fp.setBlueprintId(modifiedFoodProduct.getBlueprintId());
                    fp.setInvoiceId(modifiedFoodProduct.getInvoiceId());
                    fp.setReceiptId(modifiedFoodProduct.getReceiptId());
                    fp.setLocation(modifiedFoodProduct.getLocation());
                    fp.setUseByDate(modifiedFoodProduct.getUseByDate());
                    fp.setQuantity(modifiedFoodProduct.getQuantity());
                    return foodProductJpaRepository.save(fp);
                })
                .orElseGet(() -> {
                    modifiedFoodProduct.setFoodProductId(foodProductId);
                    return foodProductJpaRepository.save(modifiedFoodProduct);
                });
    }

}

