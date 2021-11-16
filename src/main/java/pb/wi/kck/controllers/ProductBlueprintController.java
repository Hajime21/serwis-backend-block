package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductBlueprintDTO;
import pb.wi.kck.model.Product;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.services.ProductBlueprintService;
import pb.wi.kck.services.ProductService;

import java.util.ArrayList;
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
    }

    private ProductBlueprintDTO convertToDto(ProductBlueprint productBlueprint) {
        ProductBlueprintDTO productBlueprintDTO = modelMapper.map(productBlueprint, ProductBlueprintDTO.class);
        productBlueprintDTO.setDependingProducts(null);
        //postDto.setSubmissionDate(post.getSubmissionDate(),userService.getCurrentUser().getPreference().getTimezone());
        return productBlueprintDTO;
    }

    private ProductBlueprint convertToEntity(ProductBlueprintDTO productBlueprintDTO) throws ParseException {
        ProductBlueprint productBlueprint = modelMapper.map(productBlueprintDTO, ProductBlueprint.class);
        //productBlueprint.setSubmissionDate(productBlueprintDTO.getSubmissionDateConverted(userService.getCurrentUser().getPreference().getTimezone()));

        if (productBlueprintDTO.getProductBlueprintId() != null) {
            ProductBlueprint oldProductBlueprint = productBlueprintService.getProductBlueprintById(productBlueprintDTO.getProductBlueprintId());

            ArrayList<Product> tempProducts = new ArrayList<>();
            for (var product : oldProductBlueprint.getDependingProducts()) {
                tempProducts.add(Product.productBuilder().productId(product.getProductId()).build());
            }

            productBlueprint.setDependingProducts(tempProducts.stream().toList());
            //post.setSent(oldPost.isSent());
        }
        return productBlueprint;
    }

    @GetMapping()
    public List<ProductBlueprintDTO> getAll() {
        List<ProductBlueprint> productBlueprints = productBlueprintService.getAll();
        return productBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<ProductBlueprintDTO> getProductBlueprintsPage(@PathVariable Integer pageNumber) {
        List<ProductBlueprint> posts = productBlueprintService.getProductBlueprintPageList(pageNumber, 33, "ASC", "productBlueprintId");
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductBlueprintDTO createProductBlueprint(@RequestBody ProductBlueprintDTO productBlueprintDTO) throws ParseException {
        ProductBlueprint productBlueprint = convertToEntity(productBlueprintDTO);
        ProductBlueprint productBlueprintCreated = productBlueprintService.createProductBlueprint(productBlueprint);
        return convertToDto(productBlueprintCreated);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductBlueprintDTO getProductBlueprint(@PathVariable Integer id) {
        return convertToDto(productBlueprintService.getProductBlueprintById(id));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductBlueprintDTO updateProductBlueprint(@RequestBody ProductBlueprintDTO productBlueprintDTO, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        ProductBlueprint productBlueprint = convertToEntity(productBlueprintDTO);
        productBlueprintService.updateProductBlueprint(productBlueprint);
        return convertToDto(productBlueprintService.getProductBlueprintById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBlueprint(@PathVariable Integer id) {
        productBlueprintService.deleteProductBlueprintById(id);
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

