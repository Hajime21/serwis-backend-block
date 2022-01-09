package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.BarcodeDto;
import pb.wi.kck.model.Barcode;
import pb.wi.kck.services.BarcodeService;
import pb.wi.kck.services.ProductBlueprintService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeController {

    private final ProductBlueprintService productBlueprintService;

    private final BarcodeService barcodeService;

    private final ModelMapper modelMapper;

    BarcodeController(ProductBlueprintService productBlueprintService, BarcodeService barcodeService, ModelMapper modelMapper) {
        this.barcodeService = barcodeService;
        this.productBlueprintService = productBlueprintService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private BarcodeDto convertToDto(Barcode barcode) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(barcode);
        BarcodeDto barcodeDto = new BarcodeDto(barcode, barcode.getProductBlueprint().getProductBlueprintId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(barcodeDto);
        return barcodeDto;
    }

    private Barcode convertToEntity(BarcodeDto barcodeDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO BARCODE'U DO ZMAPOWANIA ======");
        System.out.println(barcodeDto);
        Barcode barcode = new Barcode(barcodeDto, productBlueprintService.getById(barcodeDto.getProductBlueprintId()));
        System.out.println("======== ZMAPOWANY BARCODE ======");
        System.out.println(barcode);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }
        return barcode;
    }

    @GetMapping()
    public List<BarcodeDto> getAll() {
        List<Barcode> barcodes = barcodeService.getAll();
        return barcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<BarcodeDto> getBarcodesPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, pageSize);
        return barcodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public BarcodeDto getBarcode(@PathVariable Integer id) {
        return convertToDto(barcodeService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<Barcode> findBarcode(@RequestParam String code, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return barcodeService.findAllByCodePage(code, pageNumber, pageSize);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BarcodeDto createBarcode(@RequestBody BarcodeDto barcodeDto) throws ParseException {
        Barcode barcode = convertToEntity(barcodeDto);
        Barcode barcodeCreated = barcodeService.create(barcode);
        return convertToDto(barcodeCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BarcodeDto updateBarcode(@RequestBody BarcodeDto barcodeDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (barcodeDto.getBarcodeId() != null && barcodeDto.getBarcodeId() != 0 && barcodeDto.getBarcodeId() != id) {
            System.out.println("Identyfikatory kodu kreskowego w requeście PUT niezgodne! - " + barcodeDto.getBarcodeId().toString() + id.toString());
        }
        Barcode barcode = convertToEntity(barcodeDto);
        barcodeService.update(barcode);
        return convertToDto(barcodeService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBarcode(@PathVariable Integer id) {
        barcodeService.deleteById(id);
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

