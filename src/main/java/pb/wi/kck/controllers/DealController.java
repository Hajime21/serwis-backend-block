package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.DealDto;
import pb.wi.kck.model.Deal;
import pb.wi.kck.services.CompanyService;
import pb.wi.kck.services.DealService;
import pb.wi.kck.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    private final CompanyService companyService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    DealController(DealService dealService, CompanyService companyService, UserService userService, ModelMapper modelMapper) {
        this.dealService = dealService;
        this.companyService = companyService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private DealDto convertToDto(Deal deal) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(deal);
        DealDto dealDto = new DealDto(deal, deal.getCompany().getCompanyId(), deal.getUser().getUserId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(dealDto);
        return dealDto;
    }

    private Deal convertToEntity(DealDto dealDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO DEALA DO ZMAPOWANIA ======");
        System.out.println(dealDto);
        Deal deal = new Deal(dealDto, companyService.getById(dealDto.getCompanyId()), userService.getById(dealDto.getUserId()));
        System.out.println("======== ZMAPOWANY DEAL ======");
        System.out.println(deal);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return deal;
    }

    @GetMapping()
    public List<DealDto> getAll() {
        List<Deal> deals = dealService.getAll();
        return deals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<DealDto> getDealsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Deal> deals = dealService.getPageList(pageNumber, pageSize);
        return deals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public DealDto getDealById(@PathVariable Integer id) {
        return convertToDto(dealService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<DealDto> findDeal(@RequestParam LocalDate purchaseDate, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<Deal> deals = dealService.findAllByDatePage(purchaseDate, pageNumber, pageSize);
        return deals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DealDto createDeal(@RequestBody DealDto dealDto) throws ParseException {
        Deal deal = convertToEntity(dealDto);
        Deal dealCreated = dealService.create(deal);
        return convertToDto(dealCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DealDto updateDeal(@RequestBody DealDto dealDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (dealDto.getDealId() != null && dealDto.getDealId() != 0 && !Objects.equals(dealDto.getDealId(), id)) {
            System.out.println("Identyfikatory kodu kreskowego w requeście PUT niezgodne! - " + dealDto.getDealId().toString() + id.toString());
        }
        Deal deal = convertToEntity(dealDto);
        dealService.update(deal);
        return convertToDto(dealService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeal(@PathVariable Integer id) {
        dealService.deleteById(id);
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

