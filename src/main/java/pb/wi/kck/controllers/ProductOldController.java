package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductOldDto;
import pb.wi.kck.model.ProductOld;
import pb.wi.kck.services.ProductBlueprintWithBarcodeService;
import pb.wi.kck.services.ProductOldService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products/generic")
public class ProductOldController {

    private final ProductOldService productOldService;

    private final ProductBlueprintWithBarcodeService productBlueprintWithBarcodeService;

    private final ModelMapper modelMapper;

    ProductOldController(ProductOldService productOldService, ProductBlueprintWithBarcodeService productBlueprintWithBarcodeService, ModelMapper modelMapper) {
        this.productOldService = productOldService;
        this.productBlueprintWithBarcodeService = productBlueprintWithBarcodeService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private ProductOldDto convertToDto(ProductOld productOld) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(productOld);
        ProductOldDto productOldDto = new ProductOldDto(productOld, productOld.getProductBlueprintWithBarcode().getProductBlueprintId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(productOldDto);
        return productOldDto;
    }

    private ProductOld convertToEntity(ProductOldDto productOldDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO Product DO ZMAPOWANIA ======");
        System.out.println(productOldDto);
        ProductOld productOld = new ProductOld(productOldDto, productBlueprintWithBarcodeService.getById(productOldDto.getProductBlueprintWithBarcodeId()));
        System.out.println("======== ZMAPOWANY Product ======");
        System.out.println(productOld);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }
        return productOld;
    }

    @GetMapping()
    public List<ProductOldDto> getAll() {
        List<ProductOld> productOlds = productOldService.getAll();
        return productOlds.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<ProductOldDto> getProductOldsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<ProductOld> productOlds = productOldService.getPageList(pageNumber, pageSize);
        return productOlds.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductOldDto getProductOldById(@PathVariable Integer id) {
        return convertToDto(productOldService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<ProductOldDto> findProductOld(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductOld> productOlds = productOldService.findAllByProductBlueprintName(str, pageNumber, pageSize);
        return productOlds.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductOldDto createProductOld(@RequestBody ProductOldDto productOldDto) throws ParseException {
        ProductOld productOld = convertToEntity(productOldDto);
        ProductOld productOldCreated = productOldService.create(productOld);
        return convertToDto(productOldCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductOldDto updateProductOld(@RequestBody ProductOldDto productOldDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (productOldDto.getProductId() != null && productOldDto.getProductId() != 0 && !Objects.equals(productOldDto.getProductId(), id)) {
            System.out.println("Identyfikatory produktu w requeście PUT niezgodne! - " + productOldDto.getProductId().toString() + id.toString());
        }
        ProductOld productOld = convertToEntity(productOldDto);
        productOldService.update(productOld);
        return convertToDto(productOldService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) {
        productOldService.deleteById(id);
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

