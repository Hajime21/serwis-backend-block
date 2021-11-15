package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.model.Product;
import pb.wi.kck.repositories.ProductJpaRepository;
import pb.wi.kck.server.exceptions.ProductNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/products/generic")
public class ProductController {

    private final ProductJpaRepository productJpaRepository;

    ProductController(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @GetMapping()
    List<Product> getAll() {
        return productJpaRepository.findAll();
    }

    @PostMapping("/new")
    Product newProduct(@RequestBody Product newProduct) {
        return productJpaRepository.save(newProduct);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Product getProduct(@PathVariable Integer productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProduct(@PathVariable Integer productId) {
        productJpaRepository.deleteById(productId);
    }

    @PutMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Product modifyProduct(@PathVariable Integer productId, @RequestBody Product modifiedProduct) {
        return productJpaRepository.findById(productId)
                .map(p -> {
                    p.setProductId(modifiedProduct.getProductId());
                    p.setBlueprintId(modifiedProduct.getBlueprintId());
                    p.setInvoiceId(modifiedProduct.getInvoiceId());
                    p.setReceiptId(modifiedProduct.getReceiptId());
                    p.setLocation(modifiedProduct.getLocation());
                    p.setUseByDate(modifiedProduct.getUseByDate());
                    p.setQuantity(modifiedProduct.getQuantity());
                    return productJpaRepository.save(p);
                })
                .orElseGet(() -> {
                    modifiedProduct.setProductId(productId);
                    return productJpaRepository.save(modifiedProduct);
                });
    }

}

