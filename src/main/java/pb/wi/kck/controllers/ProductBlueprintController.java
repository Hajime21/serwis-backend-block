package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.exceptions.ProductBlueprintNotFoundException;
import pb.wi.kck.repositories.ProductBlueprintJpaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/blueprints/generic")
public class ProductBlueprintController {

    private final ProductBlueprintJpaRepository productBlueprintJpaRepository;

    ProductBlueprintController(ProductBlueprintJpaRepository productBlueprintJpaRepository) {
        this.productBlueprintJpaRepository = productBlueprintJpaRepository;
    }

    @GetMapping()
    List<ProductBlueprint> getAll() {
        return productBlueprintJpaRepository.findAll();
    }

    @PostMapping("/new")
    ProductBlueprint newProductBlueprint(@RequestBody ProductBlueprint newProductBlueprint) {
        return productBlueprintJpaRepository.save(newProductBlueprint);
    }

    @GetMapping(value = "/{productBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductBlueprint getProductBlueprint(@PathVariable Integer productBlueprintId) {
        return productBlueprintJpaRepository.findById(productBlueprintId)
                .orElseThrow(() -> new ProductBlueprintNotFoundException(productBlueprintId));
    }

    @DeleteMapping(value = "/{productBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProductBlueprint(@PathVariable Integer productBlueprintId) {
        productBlueprintJpaRepository.deleteById(productBlueprintId);
    }

    @PutMapping(value = "/{productBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductBlueprint modifyProductBlueprint(@PathVariable Integer productBlueprintId, @RequestBody ProductBlueprint modifiedProductBlueprint) {
        return productBlueprintJpaRepository.findById(productBlueprintId)
                .map(pb -> {
                    pb.setName(modifiedProductBlueprint.getName());
                    pb.setBarcode(modifiedProductBlueprint.getBarcode());
                    pb.setBarcodeType(modifiedProductBlueprint.getBarcodeType());
                    pb.setDescription(modifiedProductBlueprint.getDescription());
                    pb.setImgPath(modifiedProductBlueprint.getImgPath());
                    pb.setManufacturer(modifiedProductBlueprint.getManufacturer());
                    pb.setModificationDate(modifiedProductBlueprint.getModificationDate());
                    pb.setTargetQuantity(modifiedProductBlueprint.getTargetQuantity());
                    return productBlueprintJpaRepository.save(pb);
                })
                .orElseGet(() -> {
                    modifiedProductBlueprint.setProductBlueprintId(productBlueprintId);
                    return productBlueprintJpaRepository.save(modifiedProductBlueprint);
                });
    }

}

