package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.exceptions.ProductBlueprintNotFoundException;
import pb.wi.kck.repository.ProductBlueprintRepository;

import java.util.List;

@RestController
@RequestMapping("/api/blueprint")
public class ProductBlueprintController {

    private final ProductBlueprintRepository productBlueprintRepository;

    ProductBlueprintController(ProductBlueprintRepository productBlueprintRepository) {
        this.productBlueprintRepository = productBlueprintRepository;
    }

    @GetMapping()
    List<ProductBlueprint> getAll() {
        return productBlueprintRepository.findAll();
    }

    @PostMapping("/new")
    ProductBlueprint newProductBlueprint(@RequestBody ProductBlueprint newProductBlueprint) {
        return productBlueprintRepository.save(newProductBlueprint);
    }

    @GetMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductBlueprint getProductBlueprint(@PathVariable Integer blueprintId) {
        return productBlueprintRepository.findById(blueprintId)
                .orElseThrow(() -> new ProductBlueprintNotFoundException(blueprintId));
    }

    @DeleteMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProductBlueprint(@PathVariable Integer blueprintId) {
        productBlueprintRepository.deleteById(blueprintId);
    }

    @PutMapping(value = "/{blueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductBlueprint modifyProductBlueprint(@PathVariable Integer blueprintId, @RequestBody ProductBlueprint modifiedProductBlueprint) {
        return productBlueprintRepository.findById(blueprintId)
                .map(pb -> {
                    pb.setName(modifiedProductBlueprint.getName());
                    pb.setBarcode(modifiedProductBlueprint.getBarcode());
                    pb.setBarcodeType(modifiedProductBlueprint.getBarcodeType());
                    pb.setDescription(modifiedProductBlueprint.getDescription());
                    pb.setImgPath(modifiedProductBlueprint.getImgPath());
                    pb.setManufacturer(modifiedProductBlueprint.getManufacturer());
                    pb.setModificationDate(modifiedProductBlueprint.getModificationDate());
                    pb.setTargetQuantity(modifiedProductBlueprint.getTargetQuantity());
                    return productBlueprintRepository.save(pb);
                })
                .orElseGet(() -> {
                    modifiedProductBlueprint.setBlueprintId(blueprintId);
                    return productBlueprintRepository.save(modifiedProductBlueprint);
                });
    }

}

