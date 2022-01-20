package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.ProductDto;
import pb.wi.kck.model.Product;
import pb.wi.kck.services.DealService;
import pb.wi.kck.services.LocationService;
import pb.wi.kck.services.ProductBlueprintService;
import pb.wi.kck.services.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products/generic")
public class ProductController {

    private final ProductService productService;

    private final ProductBlueprintService productBlueprintService;

    private final DealService dealService;

    private final LocationService locationService;

    private final ModelMapper modelMapper;

    ProductController(ProductService productService, ProductBlueprintService productBlueprintService, DealService dealService, LocationService locationService, ModelMapper modelMapper) {
        this.productService = productService;
        this.productBlueprintService = productBlueprintService;
        this.dealService = dealService;
        this.locationService= locationService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private ProductDto convertToDto(Product product) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(product);
        ProductDto productDto = new ProductDto(product, product.getProductBlueprint().getProductBlueprintId(), product.getDeal().getDealId(), product.getLocation().getLocationId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(productDto);
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO Product DO ZMAPOWANIA ======");
        System.out.println(productDto);
        Product product = new Product(productDto, productBlueprintService.getById(productDto.getProductBlueprintId()), dealService.getById(productDto.getDealId()), locationService.getById(productDto.getLocationId()));
        System.out.println("======== ZMAPOWANY Product ======");
        System.out.println(product);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return product;
    }

    @GetMapping()
    public List<ProductDto> getAll() {
        List<Product> products = productService.getAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<ProductDto> getProductsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Product> products = productService.getPageList(pageNumber, pageSize);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable Integer id) {
        return convertToDto(productService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<ProductDto> findProduct(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<Product> products = productService.findAllByProductBlueprintName(name, pageNumber, pageSize);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) throws ParseException {
        Product product = convertToEntity(productDto);
        Product productCreated = productService.create(product);
        return convertToDto(productCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (productDto.getProductId() != null && productDto.getProductId() != 0 && !Objects.equals(productDto.getProductId(), id)) {
            System.out.println("Identyfikatory produktu w requeście PUT niezgodne! - " + productDto.getProductId().toString() + id.toString());
        }
        Product product = convertToEntity(productDto);
//        Product productOld = productService.getById(productDto.getProductId());
//        product.setProductBlueprint(productBlueprintService.getById(productOld.getProductBlueprint()));
        productService.update(product);
        return convertToDto(productService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
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

