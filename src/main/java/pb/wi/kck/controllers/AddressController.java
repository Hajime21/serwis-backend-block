package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.AddressDto;
import pb.wi.kck.model.Address;
import pb.wi.kck.services.AddressService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    private final ModelMapper modelMapper;

    AddressController(AddressService addressService, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private AddressDto convertToDto(Address address) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(address);
        AddressDto addressDto = new AddressDto(address);
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(addressDto);
        return addressDto;
    }

    private Address convertToEntity(AddressDto addressDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO ADRESU DO ZMAPOWANIA ======");
        System.out.println(addressDto);
        Address address = new Address(addressDto);
        System.out.println("======== ZMAPOWANY ADRES ======");
        System.out.println(address);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("CHUJ");
//            //post.setSent(oldPost.isSent());
//        }
        return address;
    }

    @GetMapping()
    public List<AddressDto> getAll() {
        List<Address> addresses = addressService.getAll();
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<AddressDto> getAddressesPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Address> addresses = addressService.getAllPage(pageNumber, pageSize);
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public AddressDto getAddressById(@PathVariable Integer id) {
        return convertToDto(addressService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<AddressDto> findAddress(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<Address> addresses = addressService.findAllByAnythingPage(str, pageNumber, pageSize);
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AddressDto createAddress(@RequestBody AddressDto addressDto) throws ParseException {
        Address address = convertToEntity(addressDto);
        Address addressCreated = addressService.create(address);
        return convertToDto(addressCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto updateAddress(@RequestBody AddressDto addressDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (addressDto.getAddressId() != null && addressDto.getAddressId() != 0 && !Objects.equals(addressDto.getAddressId(), id)) {
            System.out.println("Identyfikatory adresu w requeście PUT niezgodne! - " + addressDto.getAddressId().toString() + id.toString());
        }
        Address address = convertToEntity(addressDto);
        addressService.update(address);
        return convertToDto(addressService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteById(id);
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

