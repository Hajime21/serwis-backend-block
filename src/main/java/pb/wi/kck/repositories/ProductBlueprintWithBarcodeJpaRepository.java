package pb.wi.kck.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.wi.kck.model.ProductBlueprintWithBarcode;

@Repository
public interface ProductBlueprintWithBarcodeJpaRepository extends JpaRepository<ProductBlueprintWithBarcode, Integer>, PagingAndSortingRepository<ProductBlueprintWithBarcode, Integer> {
    //@Query("select * from ProductBlueprint u")
    Page<ProductBlueprintWithBarcode> findAll(Pageable pageReq);
    Page<ProductBlueprintWithBarcode> findProductBlueprintWithBarcodeByBarcodeContainingOrProductBlueprintNameContainingOrManufacturerContainingOrDescriptionContainingOrderByProductBlueprintName(String barcode, String productBlueprintName, String manufacturer, String description, Pageable pageable);
    Page<ProductBlueprintWithBarcode> findProductBlueprintWithBarcodeByProductBlueprintNameContainingOrderByProductBlueprintNameAsc(String name, Pageable pageReq);
    Page<ProductBlueprintWithBarcode> findProductBlueprintWithBarcodeByDescriptionContainingOrderByProductBlueprintNameAsc(String description, Pageable pageReq);
    Page<ProductBlueprintWithBarcode> findProductBlueprintWithBarcodeByManufacturerOrderByProductBlueprintNameAsc(String manufacturer, Pageable pageReq);
    ProductBlueprintWithBarcode getByProductBlueprintId(Integer integer);
}

