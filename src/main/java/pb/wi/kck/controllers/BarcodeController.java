package pb.wi.kck.controllers;

import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.Barcode;
import pb.wi.kck.services.BarcodeService;
import pb.wi.kck.services.ProductBlueprintService;

import java.util.List;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeController {

    private final ProductBlueprintService productBlueprintService;

    private final BarcodeService barcodeService;

    BarcodeController(ProductBlueprintService productBlueprintService, BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
        this.productBlueprintService = productBlueprintService;
    }

    @GetMapping()
    public List<Barcode> getAll() {
        List<Barcode> barcodes = barcodeService.getAll();
        return barcodes;
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<Barcode> getBarcodesPage(@PathVariable Integer pageNumber) {
        List<Barcode> posts = barcodeService.getPageList(pageNumber, 33, "ASC", "barcodeId");
        return posts;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Barcode createBarcode(@RequestBody Barcode newBarcode) throws ParseException {
        Barcode barcodeCreated = barcodeService.create(newBarcode);
        return barcodeCreated;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Barcode getBarcode(@PathVariable Integer id) {
        return barcodeService.getById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Barcode updateBarcode(@RequestBody Barcode barcode, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        barcodeService.update(barcode);
        return barcodeService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBarcode(@PathVariable Integer id) {
        barcodeService.deleteById(id);
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

