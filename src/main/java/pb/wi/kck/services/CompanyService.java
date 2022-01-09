package pb.wi.kck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pb.wi.kck.model.Company;
import pb.wi.kck.repositories.CompanyJpaRepository;

import java.util.List;

@Service
public class CompanyService { //implements ProductBlueprintService {

    private final CompanyJpaRepository companyJpaRepository;

    @Autowired
    public CompanyService(CompanyJpaRepository companyJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
    }

    public Company getById(Integer id) {
        return companyJpaRepository.getById(id);
    }

    public Company create(Company company) {
        return companyJpaRepository.save(company);
    }

    public void update(Company company) {
        companyJpaRepository.save(company);
    }

    public List<Company> getAll() {
        List<Company> companies = companyJpaRepository.findAll();
        return companies;
    }

    //public List<Company> getPageList(int page, int size, String sortDir, String sort) {
    public List<Company> getAllPage(int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "companyId");
        Page<Company> respondedPage = companyJpaRepository.findAll(pageReq);
        return respondedPage.getContent();
    }

    public List<Company> findAllByAnythingPage(String str, int pageNumber, int pageSize) {
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "companyId");
        Page<Company> respondedPage = companyJpaRepository.findAllByCompanyNIPContainingOrCompanyNameContaining(str, str, pageReq);
        return respondedPage.getContent();
    }

    public void deleteById(Integer id) {
        companyJpaRepository.deleteById(id);
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
