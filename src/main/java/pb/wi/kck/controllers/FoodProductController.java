package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.FoodProductDto;
import pb.wi.kck.model.FoodProduct;
import pb.wi.kck.services.DealService;
import pb.wi.kck.services.FoodProductBlueprintService;
import pb.wi.kck.services.FoodProductService;
import pb.wi.kck.services.LocationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products/food")
public class FoodProductController {

    private final FoodProductService foodProductService;

    private final FoodProductBlueprintService foodProductBlueprintService;

    private final DealService dealService;

    private final LocationService locationService;

    private final ModelMapper modelMapper;

    FoodProductController(FoodProductService foodProductService, FoodProductBlueprintService foodProductBlueprintService, DealService dealService, LocationService locationService, ModelMapper modelMapper) {
        this.foodProductService = foodProductService;
        this.foodProductBlueprintService = foodProductBlueprintService;
        this.dealService = dealService;
        this.locationService= locationService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private FoodProductDto convertToDto(FoodProduct foodProduct) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(foodProduct);
        FoodProductDto foodProductDto = new FoodProductDto(foodProduct, foodProduct.getFoodProductBlueprint().getFoodProductBlueprintId(), foodProduct.getDeal().getDealId(), foodProduct.getLocation().getLocationId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(foodProductDto);
        return foodProductDto;
    }

    private FoodProduct convertToEntity(FoodProductDto foodProductDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO FoodProduct DO ZMAPOWANIA ======");
        System.out.println(foodProductDto);
        FoodProduct foodProduct = new FoodProduct();
        if(foodProductDto.getLocationId() != null) {
            foodProduct = new FoodProduct(foodProductDto, foodProductBlueprintService.getById(foodProductDto.getFoodProductBlueprintId()), dealService.getById(foodProductDto.getDealId()), locationService.getById(foodProductDto.getLocationId()));
        } else {
            foodProduct = new FoodProduct(foodProductDto, foodProductBlueprintService.getById(foodProductDto.getFoodProductBlueprintId()), dealService.getById(foodProductDto.getDealId()), null);
        }
        System.out.println("======== ZMAPOWANY FoodProduct ======");
        System.out.println(foodProduct);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return foodProduct;
    }

    @GetMapping()
    public List<FoodProductDto> getAll() {
        List<FoodProduct> foodProducts = foodProductService.getAll();
        return foodProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<FoodProductDto> getFoodProductsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<FoodProduct> foodProducts = foodProductService.getPageList(pageNumber, pageSize);
        return foodProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public FoodProductDto getFoodProductById(@PathVariable Integer id) {
        return convertToDto(foodProductService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<FoodProductDto> findFoodProduct(@RequestParam String name, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<FoodProduct> foodProducts = foodProductService.findAllByFoodProductBlueprintName(name, pageNumber, pageSize);
        return foodProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FoodProductDto createFoodProduct(@RequestBody FoodProductDto foodProductDto) throws ParseException {
        FoodProduct foodProduct = convertToEntity(foodProductDto);
        FoodProduct foodProductCreated = foodProductService.create(foodProduct);
        return convertToDto(foodProductCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodProductDto updateFoodProduct(@RequestBody FoodProductDto foodProductDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (foodProductDto.getFoodProductId() != null && foodProductDto.getFoodProductId() != 0 && !Objects.equals(foodProductDto.getFoodProductId(), id)) {
            System.out.println("Identyfikatory kodu kreskowego w requeście PUT niezgodne! - " + foodProductDto.getFoodProductId().toString() + id.toString());
        }
        FoodProduct foodProduct = convertToEntity(foodProductDto);
        FoodProduct foodProductOld = foodProductService.getById(foodProductDto.getFoodProductId());
        //foodProduct.setFoodProductBlueprint(foodProductOld.getFoodProductBlueprint());
        //foodProduct.setDeal(foodProductOld.getDeal());
        //foodProduct.setLocation(foodProductOld.getLocation());
        foodProductService.update(foodProduct);
        return convertToDto(foodProductService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFoodProduct(@PathVariable Integer id) {
        foodProductService.deleteById(id);
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

