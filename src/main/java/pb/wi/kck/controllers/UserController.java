package pb.wi.kck.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.dto.UserDto;
import pb.wi.kck.model.User;
import pb.wi.kck.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(LombokBuilderNamingConvention.INSTANCE)
                .setDestinationNameTransformer(LombokBuilderNameTransformer.INSTANCE);
    }

    private UserDto convertToDto(User user) {
        //BarcodeDto barcodeDto = modelMapper.map(barcode, BarcodeDto.BarcodeDtoBuilder.class).build();
        System.out.println("-------- OBIEKT DO ZMAPOWANIA ------");
        System.out.println(user);
        UserDto userDto = new UserDto(user);
        System.out.println("-------- ZMAPOWANE DTO OBIEKTU ------");
        System.out.println(userDto);
        return userDto;
    }

    private User convertToEntity(UserDto userDto) throws ParseException {
        //Barcode barcode = modelMapper.map(barcodeDto, Barcode.BarcodeBuilder.class).build();
        System.out.println("======== DTO USERA DO ZMAPOWANIA ======");
        System.out.println(userDto);
        User user = new User(userDto);
        System.out.println("======== ZMAPOWANY USER ======");
        System.out.println(user);

//        if (barcodeDto.getBarcodeId() != null) {
//            Barcode oldBarcode = barcodeService.getById(barcodeDto.getBarcodeId());
//            System.out.println("ez");
//            //post.setSent(oldPost.isSent());
//        }
        return user;
    }

    @GetMapping()
    public List<UserDto> getAll() {
        List<User> users = userService.getAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<UserDto> getUsersPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        //List<Barcode> barcodes = barcodeService.getAllPage(pageNumber, 33, "ASC", "barcodeId");
        List<User> users = userService.getPageList(pageNumber, pageSize);
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public UserDto getUsersById(@PathVariable Integer id) {
        return convertToDto(userService.getById(id));
    }

    @GetMapping(value = "/s")
    @ResponseBody
    public List<UserDto> findUser(@RequestParam String str, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<User> users = userService.findAllByAnythingPage(str, pageNumber, pageSize);
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto createUser(@RequestBody UserDto userDto) throws ParseException {
        User user = convertToEntity(userDto);
        User userCreated = userService.create(user);
        return convertToDto(userCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) throws ParseException { //produces = MediaType.APPLICATION_JSON_VALUE
        if (userDto.getUserId() != null && userDto.getUserId() != 0 && !Objects.equals(userDto.getUserId(), id)) {
            System.out.println("Identyfikatory usera w requeście PUT niezgodne! - " + userDto.getUserId().toString() + id.toString());
        }
        User user = convertToEntity(userDto);
        User userOld = userService.getById(userDto.getUserId());
        user.setDeals(userOld.getDeals());
        userService.update(user);
        return convertToDto(userService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
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

