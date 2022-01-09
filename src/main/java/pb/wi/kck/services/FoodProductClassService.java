package pb.wi.kck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pb.wi.kck.model.FoodProductClass;
import pb.wi.kck.repositories.FoodProductClassJpaRepository;

import java.util.List;

@Service
public class FoodProductClassService { //implements ProductBlueprintService {

    private final FoodProductClassJpaRepository foodProductClassJpaRepository;

    @Autowired
    public FoodProductClassService(FoodProductClassJpaRepository foodProductClassJpaRepository) {
        this.foodProductClassJpaRepository = foodProductClassJpaRepository;
    }

    public FoodProductClass getById(Integer id) {
        return foodProductClassJpaRepository.getById(id);
    }

    public FoodProductClass create(FoodProductClass foodProductClass) {
        return foodProductClassJpaRepository.save(foodProductClass);
    }

    public void update(FoodProductClass foodProductClass) {
        foodProductClassJpaRepository.save(foodProductClass);
    }

    public List<FoodProductClass> getAll() {
        List<FoodProductClass> foodProductClasses = foodProductClassJpaRepository.findAll();
        return foodProductClasses;
    }

    //public List<FoodProductClass> getPageList(int page, int size, String sortDir, String sort) {
    public List<FoodProductClass> getAllPage(int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "foodProductClassId");
        Page<FoodProductClass> respondedPage = foodProductClassJpaRepository.findAll(pageReq);
        return respondedPage.getContent();
    }

    public List<FoodProductClass> findAllByNamePage(String name, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "foodProductClassId");
        Page<FoodProductClass> respondedPage = foodProductClassJpaRepository.findAllByFoodProductClassNameContaining(name, pageReq);
        return respondedPage.getContent();
    }

    public void deleteById(Integer id) {
        foodProductClassJpaRepository.deleteById(id);
    }


//    public ProductBlueprintDTO findById(Integer productBlueprintId) {
//        var temp = productBlueprintJpaRepository.findById(productBlueprintId;
//        return convertToDto()
//                .orElseThrow(() -> new ProductBlueprintNotFoundException(productBlueprintId)));
//    }
//
//    public ProductBlueprintDTO save(ProductBlueprint pb) {
//        return convertToDto(productBlueprintJpaRepository.save(pb));
//    }
//
//    @Transactional
//    public void deleteById(Integer pbID) {
//        productBlueprintJpaRepository.deleteById(pbID);
//    }
//
//    public ProductBlueprint[] getAllinEntities(Integer startId, Integer count) {
//        ProductBlueprint[] temp = new ProductBlueprint[count];
//        Integer id = startId;
//        int i = 0;
//        while (i < count) {
//            temp[i] = productBlueprintJpaRepository.getById(id);
//            if(temp[i] != null) {
//                i++;
//            }
//            id++;
//        }
//        return temp;
//
//    }
//
//    public List<ProductBlueprintDTO> getAllinDTOs() {
//        ArrayList<ProductBlueprint> temp = new ArrayList<>(productBlueprintJpaRepository.findAll());
//        ArrayList<ProductBlueprintDTO> temp2 = new ArrayList<>();
//        for (var x : temp) {
//            temp2.add(convertToDto(x));
//        }
//        return temp2;
//    }
//
//        //@Override
//    public ProductBlueprintDTO[] getAllinDTOs(Integer startIndex, Integer count) {
//        ProductBlueprintDTO[] temp = new ProductBlueprintDTO[count];
//        for(int i=0; i<count; i++) {
//
//        }
//        return temp;
//    }



//    @Override
//    public ProductBlueprint convertToEntity(ProductBlueprintDTO pbDTO) {
//        ProductBlueprint productBlueprint = ProductBlueprint.productBlueprintBuilder()
//                .productBlueprintId(pbDTO.getBlueprintId())
//                .manufacturer(pbDTO.getManufacturer())
//                .barcode(pbDTO.getBarcode())
//                .barcodeType(pbDTO.getBarcodeType())
//                .description(pbDTO.getDescription())
//                .imgPath(pbDTO.getImgPath())
//                .targetQuantity(pbDTO.getTargetQuantity())
//                .modificationDate(pbDTO.getModificationDate())
//                .dependingProducts(pbDTO.getDependingProducts())
//                .build();
//        return productBlueprint;
//    }
//
//    public ProductBlueprintDTO convertToDto(ProductBlueprint pb) {
//        ProductBlueprintDTO productBlueprintDTO = ProductBlueprintDTO.productBlueprintDTOBuilder()
//                .blueprintId(pb.getProductBlueprintId())
//                .name(pb.getName())
//                .manufacturer(pb.getManufacturer())
//                .barcode(pb.getBarcode())
//                .barcodeType(pb.getBarcodeType())
//                .description(pb.getDescription())
//                .imgPath(pb.getImgPath())
//                .targetQuantity(pb.getTargetQuantity())
//                .modificationDate(pb.getModificationDate())
//                .dependingProducts(pb.getDependingProducts())
//                .build();
//        return productBlueprintDTO;
//    }

}
