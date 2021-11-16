package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductDTO;
import pb.wi.kck.model.Product;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.services.ProductBlueprintService;
import pb.wi.kck.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products/generic")
public class ProductController {

    private final ProductService productService;

    private final ProductBlueprintService productBlueprintService;

    private final ModelMapper modelMapper;

    ProductController(ProductService productService, ProductBlueprintService productBlueprintService, ModelMapper modelMapper) {
        this.productService = productService;
        this.productBlueprintService = productBlueprintService;
        this.modelMapper = modelMapper;
    }

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setDependedProductBlueprint(product.getDependedProductBlueprint());
        //postDto.setSubmissionDate(post.getSubmissionDate(),userService.getCurrentUser().getPreference().getTimezone());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO, Product.class);
        //productBlueprint.setSubmissionDate(productBlueprintDTO.getSubmissionDateConverted(userService.getCurrentUser().getPreference().getTimezone()));

        if (productDTO.getProductId() != null) {
            Product oldProduct = productService.getProductById(productDTO.getProductId());

            ProductBlueprint temp = productBlueprintService.getProductBlueprintById( productDTO.getDependedProductBlueprint().getProductBlueprintId());
            product.setDependedProductBlueprint(temp);
        }
        return product;
    }

    @GetMapping()
    public List<ProductDTO> getAll() {
        List<Product> products = productService.getAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<ProductDTO> getProductPage(@PathVariable Integer pageNumber) {
        List<Product> products = productService.getProductPageList(pageNumber, 33, "ASC", "productId");
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) throws ParseException {
        Product product = convertToEntity(productDTO);
        Product productCreated = productService.createProduct(product);
        return convertToDto(productCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        Product product = convertToEntity(productDTO);
        productService.updateProduct(product);
        return convertToDto(productService.getProductById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }



//    @PutMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    Product modifyProduct(@PathVariable Integer productId, @RequestBody Product modifiedProduct) {
//        return productJpaRepository.findById(productId)
//                .map(p -> {
//                    p.setProductId(modifiedProduct.getProductId());
//                    p.setDependedProductBlueprint(modifiedProduct.getDependedProductBlueprint());
//                    p.setInvoiceId(modifiedProduct.getInvoiceId());
//                    p.setReceiptId(modifiedProduct.getReceiptId());
//                    p.setLocation(modifiedProduct.getLocation());
//                    p.setUseByDate(modifiedProduct.getUseByDate());
//                    p.setQuantity(modifiedProduct.getQuantity());
//                    return productJpaRepository.save(p);
//                })
//                .orElseGet(() -> {
//                    modifiedProduct.setProductId(productId);
//                    return productJpaRepository.save(modifiedProduct);
//                });
//    }

}

