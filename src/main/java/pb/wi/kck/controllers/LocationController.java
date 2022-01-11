package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.LocationDto;
import pb.wi.kck.model.Location;
import pb.wi.kck.services.LocationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    private final ModelMapper modelMapper;

    LocationController(LocationService locationService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private LocationDto convertToDto(Location location) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(location);
        LocationDto locationDto = new LocationDto(location);
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(locationDto);
        return locationDto;
    }

    private Location convertToEntity(LocationDto locationDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO LOKACJI DO ZMAPOWANIA ======");
        System.out.println(locationDto);
        Location location = new Location(locationDto);
        System.out.println("======== ZMAPOWANA LOKACJA ======");
        System.out.println(location);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return location;
    }

    @GetMapping()
    public List<LocationDto> getAll() {
        List<Location> locations = locationService.getAll();
        return locations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<LocationDto> getLocationsPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<Location> locations = locationService.getAllPage(pageNumber, pageSize);
        return locations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public LocationDto getLocationById(@PathVariable Integer id) {
        return convertToDto(locationService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<LocationDto> findLocation(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<Location> locations = locationService.findAllByNamePage(str, pageNumber, pageSize);
        return locations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public LocationDto createLocation(@RequestBody LocationDto locationDto) throws ParseException {
        Location location = convertToEntity(locationDto);
        Location locationCreated = locationService.create(location);
        return convertToDto(locationCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto updateLocation(@RequestBody LocationDto locationDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (locationDto.getLocationId() != null && locationDto.getLocationId() != 0 && !Objects.equals(locationDto.getLocationId(), id)) {
            System.out.println("Identyfikatory adresu w requeście PUT niezgodne! - " + locationDto.getLocationName() + id.toString());
        }
        Location location = convertToEntity(locationDto);
        locationService.update(location);
        return convertToDto(locationService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable Integer id) {
        locationService.deleteById(id);
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

