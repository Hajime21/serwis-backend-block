package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.FoodProductClassDto;
import pb.wi.kck.model.FoodProductClass;
import pb.wi.kck.services.FoodProductClassService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fpc")
public class FoodProductClassController {

    private final FoodProductClassService foodProductClassService;

    private final ModelMapper modelMapper;

    FoodProductClassController(FoodProductClassService foodProductClassService, ModelMapper modelMapper) {
        this.foodProductClassService = foodProductClassService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private FoodProductClassDto convertToDto(FoodProductClass foodProductClass) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(foodProductClass);
        FoodProductClassDto foodProductClassDto = new FoodProductClassDto(foodProductClass);
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(foodProductClassDto);
        return foodProductClassDto;
    }

    private FoodProductClass convertToEntity(FoodProductClassDto foodProductClassDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO FPC DO ZMAPOWANIA ======");
        System.out.println(foodProductClassDto);
        FoodProductClass foodProductClass = new FoodProductClass(foodProductClassDto);
        System.out.println("======== ZMAPOWANY FPC ======");
        System.out.println(foodProductClass);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }
        return foodProductClass;
    }

    @GetMapping()
    public List<FoodProductClassDto> getAll() {
        List<FoodProductClass> foodProductClasses = foodProductClassService.getAll();
        return foodProductClasses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<FoodProductClassDto> getFoodProductClassesPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<FoodProductClass> foodProductClasses = foodProductClassService.getAllPage(pageNumber, pageSize);
        return foodProductClasses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public FoodProductClassDto getFoodProductClassById(@PathVariable Integer id) {
        return convertToDto(foodProductClassService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<FoodProductClassDto> findFoodProductClass(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<FoodProductClass> foodProductClasses = foodProductClassService.findAllByNamePage(str, pageNumber, pageSize);
        return foodProductClasses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FoodProductClassDto createFoodProductClass(@RequestBody FoodProductClassDto foodProductClassDto) throws ParseException {
        FoodProductClass foodProductClass = convertToEntity(foodProductClassDto);
        FoodProductClass foodProductClassCreated = foodProductClassService.create(foodProductClass);
        return convertToDto(foodProductClass);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodProductClassDto updateFoodProductClass(@RequestBody FoodProductClassDto foodProductClassDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (foodProductClassDto.getFoodProductClassId() != null && foodProductClassDto.getFoodProductClassId() != 0 && !Objects.equals(foodProductClassDto.getFoodProductClassName(), id)) {
            System.out.println("Identyfikatory FoodProductClass w requeście PUT niezgodne! - " + foodProductClassDto.getFoodProductClassId().toString() + id.toString());
        }
        FoodProductClass foodProductClass = convertToEntity(foodProductClassDto);
        foodProductClassService.update(foodProductClass);
        return convertToDto(foodProductClassService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFoodProductClass(@PathVariable Integer id) {
        foodProductClassService.deleteById(id);
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

