package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductBlueprintWithBarcodeDto;
import pb.wi.kck.model.ProductBlueprintWithBarcode;
import pb.wi.kck.services.ProductBlueprintWithBarcodeService;
import pb.wi.kck.services.ProductOldService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blueprints/generic")
public class ProductBlueprintWithBarcodeController {

    private final ProductBlueprintWithBarcodeService ProductBlueprintWithBarcodeService;

    private final ProductOldService productOldService;

    private final ModelMapper modelMapper;

    ProductBlueprintWithBarcodeController(ProductBlueprintWithBarcodeService ProductBlueprintWithBarcodeService, ProductOldService productOldService, ModelMapper modelMapper) {
        this.productOldService = productOldService;
        this.ProductBlueprintWithBarcodeService = ProductBlueprintWithBarcodeService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private ProductBlueprintWithBarcodeDto convertToDto(ProductBlueprintWithBarcode productBlueprintWithBarcode) {
        //ProductBlueprintWithBarcodeDto ProductBlueprintWithBarcodeDto = modelMapper.map(productBlueprint, ProductBlueprintWithBarcodeDto.ProductBlueprintWithBarcodeDtoBuilder.class).build();
        System.out.println("-------- ProductBlueprintWithBarcode DO ZMAPOWANIA ------");
        System.out.println(productBlueprintWithBarcode);
        ProductBlueprintWithBarcodeDto productBlueprintWithBarcodeDto = new ProductBlueprintWithBarcodeDto(productBlueprintWithBarcode);
        System.out.println("-------- ZMAPOWANE DTO ProductBlueprintWithBarcode ------");
        System.out.println(productBlueprintWithBarcodeDto);
        //ProductBlueprintWithBarcodeDto.setDependingProducts(null);
        //postDto.setSubmissionDate(post.getSubmissionDate(),userService.getCurrentUser().getPreference().getTimezone());
        return productBlueprintWithBarcodeDto;
    }

    private ProductBlueprintWithBarcode convertToEntity(ProductBlueprintWithBarcodeDto productBlueprintWithBarcodeDto) throws ParseException {
        //ProductBlueprintWithBarcode ProductBlueprintWithBarcode = modelMapper.map(ProductBlueprintWithBarcodeDto, ProductBlueprint.ProductBlueprintBuilder.class).build();
        //productBlueprint.setSubmissionDate(ProductBlueprintWithBarcodeDto.getSubmissionDateConverted(userService.getCurrentUser().getPreference().getTimezone()));
        System.out.println("======== DTO ProductBlueprintWithBarcode DO ZMAPOWANIA ======");
        System.out.println(productBlueprintWithBarcodeDto);
        ProductBlueprintWithBarcode productBlueprintWithBarcode = new ProductBlueprintWithBarcode(productBlueprintWithBarcodeDto);
        System.out.println("======== ZMAPOWANY ProductBlueprintWithBarcode ======");
        System.out.println(productBlueprintWithBarcode);

//        if (ProductBlueprintWithBarcodeDto.getProductBlueprintId() != null) {
//            ProductBlueprintWithBarcode oldProductBlueprintWithBarcode = ProductBlueprintWithBarcodeService.getById(ProductBlueprintWithBarcodeDto.getProductBlueprintId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }

        //TODO: cokolwiek

        return productBlueprintWithBarcode;
    }

    @GetMapping
    public List<ProductBlueprintWithBarcodeDto> getAll() {
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.getAll();
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<ProductBlueprintWithBarcodeDto> getProductBlueprintsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<ProductBlueprint> productBlueprints = ProductBlueprintWithBarcodeService.getPageList(pageNumber, 33, "ASC", "productBlueprintId");
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.getAllPage(pageNumber, pageSize);
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductBlueprintWithBarcodeDto ProductBlueprintWithBarcodeById(@PathVariable Integer id) {
        return convertToDto(ProductBlueprintWithBarcodeService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<ProductBlueprintWithBarcodeDto> findProductBlueprintWithBarcode(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.findAllByAnythingPage(str, pageNumber, pageSize);
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/n")
    @ResponseBody
    public List<ProductBlueprintWithBarcodeDto> findProductBlueprintWithBarcodeByName(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.findAllByNamePage(name, pageNumber, pageSize);
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/d")
    @ResponseBody
    public List<ProductBlueprintWithBarcodeDto> findProductBlueprintWithBarcodeByDescription(@RequestParam String description, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.findAllByDescriptionPage(description, pageNumber, pageSize);
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/m")
    @ResponseBody
    public List<ProductBlueprintWithBarcodeDto> findProductBlueprintWithBarcodeByManufacturer(@RequestParam String manufacturer, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprintWithBarcode> productBlueprintWithBarcodes = ProductBlueprintWithBarcodeService.findAllByNamePage(manufacturer, pageNumber, pageSize);
        return productBlueprintWithBarcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductBlueprintWithBarcodeDto createProductBlueprint(@RequestBody ProductBlueprintWithBarcodeDto productBlueprintWithBarcodeDto) throws ParseException {
        ProductBlueprintWithBarcode productBlueprintWithBarcode = convertToEntity(productBlueprintWithBarcodeDto);
        ProductBlueprintWithBarcode productBlueprintWithBarcodeCreated = ProductBlueprintWithBarcodeService.create(productBlueprintWithBarcode);
        return convertToDto(productBlueprintWithBarcodeCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductBlueprintWithBarcodeDto updateProductBlueprint(@RequestBody ProductBlueprintWithBarcodeDto productBlueprintWithBarcodeDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        ProductBlueprintWithBarcode productBlueprintWithBarcode = convertToEntity(productBlueprintWithBarcodeDto);
        ProductBlueprintWithBarcodeService.update(productBlueprintWithBarcode);
        return convertToDto(ProductBlueprintWithBarcodeService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBlueprint(@PathVariable Integer id) {
        ProductBlueprintWithBarcodeService.deleteById(id);
    }



//
//    @PutMapping(value = "/{productBlueprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    ProductBlueprintWithBarcode modifyProductBlueprint(@PathVariable Integer productBlueprintId, @RequestBody ProductBlueprintWithBarcode modifiedProductBlueprint) {
//        return ProductBlueprintWithBarcodeService.findById(productBlueprintId)
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

