package pb.wi.kck.controllers;

import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.services.ProductBlueprintService;
import pb.wi.kck.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/blueprints/generic")
public class ProductBlueprintController {

    private final ProductBlueprintService productBlueprintService;

    private final ProductService productService;

    ProductBlueprintController(ProductBlueprintService productBlueprintService, ProductService productService) {
        this.productService = productService;
        this.productBlueprintService = productBlueprintService;
    }

    @GetMapping()
    public List<ProductBlueprint> getAll() {
        List<ProductBlueprint> productBlueprints = productBlueprintService.getAll();
        return productBlueprints;
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<ProductBlueprint> getProductBlueprintsPage(@PathVariable Integer pageNumber) {
        List<ProductBlueprint> posts = productBlueprintService.getPageList(pageNumber, 33, "ASC", "productBlueprintId");
        return posts;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductBlueprint createProductBlueprint(@RequestBody ProductBlueprint newProductBlueprint) throws ParseException {
        ProductBlueprint productBlueprintCreated = productBlueprintService.create(newProductBlueprint);
        return productBlueprintCreated;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductBlueprint getProductBlueprint(@PathVariable Integer id) {
        return productBlueprintService.getById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductBlueprint updateProductBlueprint(@RequestBody ProductBlueprint productBlueprint, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        productBlueprintService.update(productBlueprint);
        return productBlueprintService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBlueprint(@PathVariable Integer id) {
        productBlueprintService.deleteById(id);
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<ProductBlueprint> findProductBlueprint(@RequestParam String name) {
        return productBlueprintService.findByName(name);
    }

//
//    @PutMapping(value = "/{productBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    ProductBlueprint modifyProductBlueprint(@PathVariable Integer productBlueprintId, @RequestBody ProductBlueprint modifiedProductBlueprint) {
//        return productBlueprintService.findById(productBlueprintId)
//                .map(pb -> {
//                    pb.setName(modifiedProductBlueprint.getName());
//                    pb.setBarcode(modifiedProductBlueprint.getBarcode());
//                    pb.setBarcodeType(modifiedProductBlueprint.getBarcodeType());
//                    pb.setDescription(modifiedProductBlueprint.getDescription());
//                    pb.setImgPath(modifiedProductBlueprint.getImgPath());
//                    pb.setManufacturer(modifiedProductBlueprint.getManufacturer());
//                    pb.setModificationDate(modifiedProductBlueprint.getModificationDate());
//                    pb.setTargetQuantity(modifiedProductBlueprint.getTargetQuantity());
//                    return productBlueprintJpaRepository.save(pb);
//                })
//                .orElseGet(() -> {
//                    modifiedProductBlueprint.setProductBlueprintId(productBlueprintId);
//                    return productBlueprintJpaRepository.save(modifiedProductBlueprint);
//                });
//    }

}

