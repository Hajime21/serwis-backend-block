package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.FoodProductBlueprintDto;
import pb.wi.kck.model.FoodProductBlueprint;
import pb.wi.kck.services.FoodProductBlueprintService;
import pb.wi.kck.services.FoodProductClassService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blueprints/food")
public class FoodProductBlueprintController {

    private final FoodProductBlueprintService foodProductBlueprintService;

    private final FoodProductClassService foodProductClassService;

    private final ModelMapper modelMapper;

    FoodProductBlueprintController(FoodProductBlueprintService foodProductBlueprintService, FoodProductClassService foodProductClassService, ModelMapper modelMapper) {
        this.foodProductBlueprintService = foodProductBlueprintService;
        this.foodProductClassService = foodProductClassService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private FoodProductBlueprintDto convertToDto(FoodProductBlueprint foodProductBlueprint) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(foodProductBlueprint);
        FoodProductBlueprintDto foodProductBlueprintDto = new FoodProductBlueprintDto(foodProductBlueprint, foodProductBlueprint.getFoodProductClass().getFoodProductClassId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(foodProductBlueprintDto);
        return foodProductBlueprintDto;
    }

    private FoodProductBlueprint convertToEntity(FoodProductBlueprintDto foodProductBlueprintDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO DEALA DO ZMAPOWANIA ======");
        System.out.println(foodProductBlueprintDto);
        FoodProductBlueprint foodProductBlueprint = new FoodProductBlueprint(foodProductBlueprintDto, foodProductClassService.getById(foodProductBlueprintDto.getFoodProductClassId()));
        System.out.println("======== ZMAPOWANY DEAL ======");
        System.out.println(foodProductBlueprint);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return foodProductBlueprint;
    }

    @GetMapping()
    public List<FoodProductBlueprintDto> getAll() {
        List<FoodProductBlueprint> foodProductBlueprints = foodProductBlueprintService.getAll();
        return foodProductBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<FoodProductBlueprintDto> getFoodProductBlueprintPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<FoodProductBlueprint> foodProductBlueprints = foodProductBlueprintService.getPageList(pageNumber, pageSize);
        return foodProductBlueprints.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public FoodProductBlueprintDto getFoodProductBlueprintById(@PathVariable Integer id) {
        return convertToDto(foodProductBlueprintService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<FoodProductBlueprintDto> findDeal(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<FoodProductBlueprint> deals = foodProductBlueprintService.findAllByAnythingPage(str, pageNumber, pageSize);
        return deals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FoodProductBlueprintDto createFoodProductBlueprint(@RequestBody FoodProductBlueprintDto foodProductBlueprintDto) throws ParseException {
        FoodProductBlueprint foodProductBlueprint = convertToEntity(foodProductBlueprintDto);
        FoodProductBlueprint foodProductBlueprintCreated = foodProductBlueprintService.create(foodProductBlueprint);
        return convertToDto(foodProductBlueprintCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodProductBlueprintDto updateFoodProductBlueprint(@RequestBody FoodProductBlueprintDto foodProductBlueprintDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (foodProductBlueprintDto.getFoodProductBlueprintId() != null && foodProductBlueprintDto.getFoodProductBlueprintId() != 0 && !Objects.equals(foodProductBlueprintDto.getFoodProductBlueprintId(), id)) {
            System.out.println("Identyfikatory kodu kreskowego w requeście PUT niezgodne! - " + foodProductBlueprintDto.getFoodProductBlueprintId().toString() + id.toString());
        }
        FoodProductBlueprint foodProductBlueprint = convertToEntity(foodProductBlueprintDto);
        foodProductBlueprintService.update(foodProductBlueprint);
        return convertToDto(foodProductBlueprintService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeal(@PathVariable Integer id) {
        foodProductBlueprintService.deleteById(id);
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

