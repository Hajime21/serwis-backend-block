package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.FoodProductBlueprint;
import pb.wi.kck.exceptions.FoodProductBlueprintNotFoundException;
import pb.wi.kck.repository.FoodProductBlueprintJpaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/foodblueprint")
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

    @GetMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint getFoodProductBlueprint(@PathVariable Integer foodBlueprintId) {
        return foodProductBlueprintJpaRepository.findById(foodBlueprintId)
                .orElseThrow(() -> new FoodProductBlueprintNotFoundException(foodBlueprintId));
    }

    @DeleteMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProductBlueprint(@PathVariable Integer foodBlueprintId) {
        foodProductBlueprintJpaRepository.deleteById(foodBlueprintId);
    }

    @PutMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint modifyProductBlueprint(@PathVariable Integer foodBlueprintId, @RequestBody FoodProductBlueprint modifiedFoodProductBlueprint) {
        return foodProductBlueprintJpaRepository.findById(foodBlueprintId)
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
                    modifiedFoodProductBlueprint.setBlueprintId(foodBlueprintId);
                    return foodProductBlueprintJpaRepository.save(modifiedFoodProductBlueprint);
                });
    }

}

