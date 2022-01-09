package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.BarcodeDto;
import pb.wi.kck.dto.ProductBlueprintDto;
import pb.wi.kck.model.Barcode;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.services.ProductBlueprintService;
import pb.wi.kck.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blueprints/generic")
public class ProductBlueprintController {

    private final ProductBlueprintService productBlueprintService;

    private final ProductService productService;

    private final ModelMapper modelMapper;

    ProductBlueprintController(ProductBlueprintService productBlueprintService, ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.productBlueprintService = productBlueprintService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private ProductBlueprintDto convertToDto(ProductBlueprint productBlueprint) {
        //ProductBlueprintDto productBlueprintDTO = modelMapper.map(productBlueprint, ProductBlueprintDto.ProductBlueprintDtoBuilder.class).build();
        System.out.println("-------- PRODUCTBLUEPRINT DO ZMAPOWANIA ------");
        System.out.println(productBlueprint);
        ProductBlueprintDto productBlueprintDTO = new ProductBlueprintDto(productBlueprint);
        System.out.println("-------- ZMAPOWANE DTO PRODUCTBLUEPRINTU ------");
        System.out.println(productBlueprintDTO);
        //productBlueprintDTO.setDependingProducts(null);
        //postDto.setSubmissionDate(post.getSubmissionDate(),userService.getCurrentUser().getPreference().getTimezone());
        return productBlueprintDTO;
    }

    private ProductBlueprint convertToEntity(ProductBlueprintDto productBlueprintDto) throws ParseException {
        //ProductBlueprint productBlueprint = modelMapper.map(productBlueprintDto, ProductBlueprint.ProductBlueprintBuilder.class).build();
        //productBlueprint.setSubmissionDate(productBlueprintDTO.getSubmissionDateConverted(userService.getCurrentUser().getPreference().getTimezone()));
        System.out.println("======== DTO PRODUCTBLUEPRINTU DO ZMAPOWANIA ======");
        System.out.println(productBlueprintDto);
        ProductBlueprint productBlueprint = new ProductBlueprint(productBlueprintDto);
        System.out.println("======== ZMAPOWANY PRODUCTBLUEPRINT ======");
        System.out.println(productBlueprint);

//        if (productBlueprintDto.getProductBlueprintId() != null) {
//            ProductBlueprint oldProductBlueprint = productBlueprintService.getById(productBlueprintDto.getProductBlueprintId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }

        //TODO: cokolwiek

        return productBlueprint;
    }

    @GetMapping()
    public List<ProductBlueprintDto> getAll() {
        List<ProductBlueprint> productBlueprints = productBlueprintService.getAll();
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<ProductBlueprintDto> getProductBlueprintsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<ProductBlueprint> productBlueprints = productBlueprintService.getPageList(pageNumber, 33, "ASC", "productBlueprintId");
        List<ProductBlueprint> productBlueprints = productBlueprintService.getAllPage(pageNumber, pageSize);
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductBlueprintDto getProductBlueprintById(@PathVariable Integer id) {
        return convertToDto(productBlueprintService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<ProductBlueprintDto> findProductBlueprint(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprint> productBlueprints = productBlueprintService.findAllByAnythingPage(str, pageNumber, pageSize);
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/n")
    @ResponseBody
    public List<ProductBlueprintDto> findProductBlueprintByName(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprint> productBlueprints = productBlueprintService.findAllByNamePage(name, pageNumber, pageSize);
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/d")
    @ResponseBody
    public List<ProductBlueprintDto> findProductBlueprintByDescription(@RequestParam String description, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprint> productBlueprints = productBlueprintService.findAllByDescriptionPage(description, pageNumber, pageSize);
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/m")
    @ResponseBody
    public List<ProductBlueprintDto> findProductBlueprintByManufacturer(@RequestParam String manufacturer, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ProductBlueprint> productBlueprints = productBlueprintService.findAllByNamePage(manufacturer, pageNumber, pageSize);
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductBlueprintDto createProductBlueprint(@RequestBody ProductBlueprintDto productBlueprintDTO) throws ParseException {
        ProductBlueprint productBlueprint = convertToEntity(productBlueprintDTO);
        ProductBlueprint productBlueprintCreated = productBlueprintService.create(productBlueprint);
        return convertToDto(productBlueprintCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductBlueprintDto updateProductBlueprint(@RequestBody ProductBlueprintDto productBlueprintDTO, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        ProductBlueprint productBlueprint = convertToEntity(productBlueprintDTO);
        productBlueprintService.update(productBlueprint);
        return convertToDto(productBlueprintService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBlueprint(@PathVariable Integer id) {
        productBlueprintService.deleteById(id);
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

