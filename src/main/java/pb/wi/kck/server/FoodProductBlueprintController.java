package pb.wi.kck.server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodblueprint")
public class FoodProductBlueprintController {

    private final FoodProductBlueprintRepository foodProductBlueprintRepository;

    FoodProductBlueprintController(FoodProductBlueprintRepository foodProductBlueprintRepository) {
        this.foodProductBlueprintRepository = foodProductBlueprintRepository;
    }

    @GetMapping()
    List<FoodProductBlueprint> getAll() {
        return foodProductBlueprintRepository.findAll();
    }

    @PostMapping("/new")
    FoodProductBlueprint newFoodProductBlueprint(@RequestBody FoodProductBlueprint newFoodProductBlueprint) {
        return foodProductBlueprintRepository.save(newFoodProductBlueprint);
    }

    @GetMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint getFoodProductBlueprint(@PathVariable Integer foodBlueprintId) {
        return foodProductBlueprintRepository.findById(foodBlueprintId)
                .orElseThrow(() -> new FoodProductBlueprintNotFoundException(foodBlueprintId));
    }

    @DeleteMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProductBlueprint(@PathVariable Integer foodBlueprintId) {
        foodProductBlueprintRepository.deleteById(foodBlueprintId);
    }

    @PutMapping(value = "/{foodBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FoodProductBlueprint modifyProductBlueprint(@PathVariable Integer foodBlueprintId, @RequestBody FoodProductBlueprint modifiedFoodProductBlueprint) {
        return foodProductBlueprintRepository.findById(foodBlueprintId)
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
                    return foodProductBlueprintRepository.save(pb);
                })
                .orElseGet(() -> {
                    modifiedFoodProductBlueprint.setBlueprintId(foodBlueprintId);
                    return foodProductBlueprintRepository.save(modifiedFoodProductBlueprint);
                });
    }

}

