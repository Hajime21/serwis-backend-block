package pb.wi.kck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pb.wi.kck.model.Barcode;
import pb.wi.kck.model.Product;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.repositories.ProductBlueprintJpaRepository;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class ProductBlueprintService { //implements ProductBlueprintService {

    private final ProductBlueprintJpaRepository productBlueprintJpaRepository;

    @Autowired
    public ProductBlueprintService(ProductBlueprintJpaRepository productBlueprintJpaRepository) {
        this.productBlueprintJpaRepository = productBlueprintJpaRepository;
    }

    public ProductBlueprint getById(Integer id) {
        return productBlueprintJpaRepository.getById(id);
    }

    public ProductBlueprint create(ProductBlueprint productBlueprint) {
        return productBlueprintJpaRepository.save(productBlueprint);
    }

    public void update(ProductBlueprint productBlueprint) {
        productBlueprintJpaRepository.save(productBlueprint);
    }

    public List<ProductBlueprint> getAll() {
        List<ProductBlueprint> productBlueprints = productBlueprintJpaRepository.findAll();
        return productBlueprints;
    }

    //public List<ProductBlueprint> getPageList(int page, int size, String sortDir, String sort) {
    public List<ProductBlueprint> getAllPage(int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productBlueprintId");
        Page<ProductBlueprint> respondedPage = productBlueprintJpaRepository.findAll(pageReq);
        return respondedPage.getContent();
    }

    public List<ProductBlueprint> findAllByNamePage(String name, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productBlueprintName");
        Page<ProductBlueprint> respondedPage = productBlueprintJpaRepository.findProductBlueprintByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(name, pageReq);
        return respondedPage.getContent();
    }

    public List<ProductBlueprint> findAllByDescriptionPage(String description, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productBlueprintName");
        Page<ProductBlueprint> respondedPage = productBlueprintJpaRepository.findProductBlueprintByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(description, pageReq);
        return respondedPage.getContent();
    }

    public List<ProductBlueprint> findAllByManufacturerPage(String manufacturer, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productBlueprintName");
        Page<ProductBlueprint> respondedPage = productBlueprintJpaRepository.findProductBlueprintByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(manufacturer, pageReq);
        return respondedPage.getContent();
    }

    public List<ProductBlueprint> findAllByAnythingPage(String str, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "productBlueprintName");
        Page<ProductBlueprint> respondedPage = productBlueprintJpaRepository.findProductBlueprintByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(str, pageReq);
        LinkedHashSet<ProductBlueprint> set = new LinkedHashSet<>(respondedPage.getContent());
        respondedPage = productBlueprintJpaRepository.findProductBlueprintByDescriptionContainingOrderByProductBlueprintNameAsc(str, pageReq);
        set.addAll(new LinkedHashSet<>(respondedPage.getContent()));
        respondedPage = productBlueprintJpaRepository.findProductBlueprintByManufacturerOrderByProductBlueprintNameAsc(str, pageReq);
        set.addAll(new LinkedHashSet<>(respondedPage.getContent()));
        return set.stream().toList();
    }

    public void deleteById(Integer id) {
        productBlueprintJpaRepository.deleteById(id);
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
