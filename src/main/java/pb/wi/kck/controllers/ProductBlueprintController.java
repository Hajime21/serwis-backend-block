package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductBlueprintDto;
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
        ProductBlueprintDto productBlueprintDTO = modelMapper.map(productBlueprint, ProductBlueprintDto.ProductBlueprintDtoBuilder.class).build();
        System.out.println("-------- PRODUCTBLUEPRINT DO ZMAPOWANIA ------");
        System.out.println(productBlueprint);
        System.out.println("-------- ZMAPOWANE DTO PRODUCTBLUEPRINTU ------");
        System.out.println(productBlueprintDTO);
        //productBlueprintDTO.setDependingProducts(null);
        //postDto.setSubmissionDate(post.getSubmissionDate(),userService.getCurrentUser().getPreference().getTimezone());
        return productBlueprintDTO;
    }

    private ProductBlueprint convertToEntity(ProductBlueprintDto productBlueprintDto) throws ParseException {
        ProductBlueprint productBlueprint = modelMapper.map(productBlueprintDto, ProductBlueprint.ProductBlueprintBuilder.class).build();
        //productBlueprint.setSubmissionDate(productBlueprintDTO.getSubmissionDateConverted(userService.getCurrentUser().getPreference().getTimezone()));
        System.out.println("======== DTO PRODUCTBLUEPRINTU DO ZMAPOWANIA ======");
        System.out.println(productBlueprintDto);
        System.out.println("======== ZMAPOWANY PRODUCTBLUEPRINT ======");
        System.out.println(productBlueprint);

        if (productBlueprintDto.getProductBlueprintId() != null) {
            ProductBlueprint oldProductBlueprint = productBlueprintService.getById(productBlueprintDto.getProductBlueprintId());
            System.out.println("CHUJ");
            //post.setSent(oldPost.isSent());
        }
        return productBlueprint;
    }

    @GetMapping()
    public List<ProductBlueprintDto> getAll() {
        List<ProductBlueprint> productBlueprints = productBlueprintService.getAll();
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<ProductBlueprintDto> getProductBlueprintsPage(@PathVariable Integer pageNumber) {
        List<ProductBlueprint> posts = productBlueprintService.getPageList(pageNumber, 33, "ASC", "productBlueprintId");
        return posts.stream()
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

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductBlueprintDto getProductBlueprint(@PathVariable Integer id) {
        return convertToDto(productBlueprintService.getById(id));
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

