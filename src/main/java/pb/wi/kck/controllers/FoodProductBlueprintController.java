package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.FoodProductBlueprint;
import pb.wi.kck.exceptions.FoodProductBlueprintNotFoundException;
import pb.wi.kck.repositories.FoodProductBlueprintJpaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/blueprints/food")
public class FoodProductBlueprintController {

    private final FoodProductBlueprintJpaRepository foodProductBlueprintJpaRepository;

    FoodProductBlueprintController(FoodProductBlueprintJpaRepository foodProductBlueprintJpaRepository) {
        this.foodProductBlueprintJpaRepository = foodProductBlueprintJpaRepository;
    }

    @GetMapping()
    List<FoodProductBlueprint> getAll() {
        return foodProductBlueprintJpaRepository.findAll();
    }

    @PostMapping("/new")
    FoodProductBlueprint newFoodProductBlueprint(@RequestBody FoodProductBlueprint newFoodProductBlueprint) {
        return foodProductBlueprintJpaRepository.save(newFoodProductBlueprint);
    }

    @GetMapping(value = "/{foodProductBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint getFoodProductBlueprint(@PathVariable Integer foodProductBlueprintId) {
        return foodProductBlueprintJpaRepository.findById(foodProductBlueprintId)
                .orElseThrow(() -> new FoodProductBlueprintNotFoundException(foodProductBlueprintId));
    }

    @DeleteMapping(value = "/{foodProductBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteFoodProductBlueprint(@PathVariable Integer foodProductBlueprintId) {
        foodProductBlueprintJpaRepository.deleteById(foodProductBlueprintId);
    }

    @PutMapping(value = "/{foodProductBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint modifyFoodProductBlueprint(@PathVariable Integer foodProductBlueprintId, @RequestBody FoodProductBlueprint modifiedFoodProductBlueprint) {
        return foodProductBlueprintJpaRepository.findById(foodProductBlueprintId)
                .map(pb -> {
                    pb.setName(modifiedFoodProductBlueprint.getName());
                    pb.setBarcode(modifiedFoodProductBlueprint.getBarcode());
                    pb.setBarcodeType(modifiedFoodProductBlueprint.getBarcodeType());
                    pb.setDescription(modifiedFoodProductBlueprint.getDescription());
                    pb.setImgPath(modifiedFoodProductBlueprint.getImgPath());
                    pb.setManufacturer(modifiedFoodProductBlueprint.getManufacturer());
                    pb.setModificationDate(modifiedFoodProductBlueprint.getModificationDate());
                    pb.setTargetQuantity(modifiedFoodProductBlueprint.getTargetQuantity());
                    pb.setCarbohydrates(modifiedFoodProductBlueprint.getCarbohydrates());
                    pb.setFat(modifiedFoodProductBlueprint.getFat());
                    pb.setProtein(modifiedFoodProductBlueprint.getProtein());
                    pb.setKcalPer100g(modifiedFoodProductBlueprint.getKcalPer100g());
                    pb.setGrammage(modifiedFoodProductBlueprint.getGrammage());
                    return foodProductBlueprintJpaRepository.save(pb);
                })
                .orElseGet(() -> {
                    modifiedFoodProductBlueprint.setFoodProductBlueprintId(foodProductBlueprintId);
                    return foodProductBlueprintJpaRepository.save(modifiedFoodProductBlueprint);
                });
    }

}

