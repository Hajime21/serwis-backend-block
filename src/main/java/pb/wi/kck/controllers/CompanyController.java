package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.CompanyDto;
import pb.wi.kck.model.Company;
import pb.wi.kck.services.AddressService;
import pb.wi.kck.services.CompanyService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final AddressService addressService;

    private final ModelMapper modelMapper;

    CompanyController(CompanyService companyService, AddressService addressService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private CompanyDto convertToDto(Company company) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(company);
        CompanyDto companyDto = new CompanyDto(company, company.getAddress().getAddressId());
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(companyDto);
        return companyDto;
    }

    private Company convertToEntity(CompanyDto companyDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO COMPANY DO ZMAPOWANIA ======");
        System.out.println(companyDto);
        Company company = new Company(companyDto, addressService.getById(companyDto.getAddressId()));
        System.out.println("======== ZMAPOWANA COMPANY ======");
        System.out.println(company);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }
        return company;
    }

    @GetMapping()
    public List<CompanyDto> getAll() {
        List<Company> companies = companyService.getAll();
        return companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<CompanyDto> getCompaniesPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Company> companies = companyService.getAllPage(pageNumber, pageSize);
        return companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public CompanyDto getCompanyById(@PathVariable Integer id) {
        return convertToDto(companyService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<Company> findCompany(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return companyService.findAllByAnythingPage(str, pageNumber, pageSize);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) throws ParseException {
        Company company = convertToEntity(companyDto);
        Company companyCreated = companyService.create(company);
        return convertToDto(companyCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (companyDto.getCompanyId() != null && companyDto.getCompanyId() != 0 && !Objects.equals(companyDto.getCompanyId(), id)) {
            System.out.println("Identyfikatory company w requeście PUT niezgodne! - " + companyDto.getCompanyId().toString() + id.toString());
        }
        Company company = convertToEntity(companyDto);
        companyService.update(company);
        return convertToDto(companyService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteById(id);
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

