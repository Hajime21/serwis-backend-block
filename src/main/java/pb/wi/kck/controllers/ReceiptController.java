package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.exceptions.ReceiptNotFoundException;
import pb.wi.kck.model.Receipt;
import pb.wi.kck.repositories.ReceiptJpaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptJpaRepository receiptJpaRepository;

    ReceiptController(ReceiptJpaRepository receiptJpaRepository) {
        this.receiptJpaRepository = receiptJpaRepository;
    }

    @GetMapping()
    List<Receipt> getAll() {
        return receiptJpaRepository.findAll();
    }

    @PostMapping("/new")
    Receipt newReceipt(@RequestBody Receipt newReceipt) {
        return receiptJpaRepository.save(newReceipt);
    }

    @GetMapping(value = "/{receiptId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Receipt getReceipt(@PathVariable Integer receiptId) {
        return receiptJpaRepository.findById(receiptId)
                .orElseThrow(() -> new ReceiptNotFoundException(receiptId));
    }

    @DeleteMapping(value = "/{receiptId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteReceipt(@PathVariable Integer receiptId) {
        receiptJpaRepository.deleteById(receiptId);
    }

    @PutMapping(value = "/{receiptId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Receipt modifyReceipt(@PathVariable Integer receiptId, @RequestBody Receipt modifiedReceipt) {
        return receiptJpaRepository.findById(receiptId)
                .map(r -> {
                    r.setReceiptId(modifiedReceipt.getReceiptId());
                    r.setReceiptValue(modifiedReceipt.getReceiptValue());
                    r.setPurchaseDate(modifiedReceipt.getPurchaseDate());
                    r.setShopName(modifiedReceipt.getShopName());
                    return receiptJpaRepository.save(r);
                })
                .orElseGet(() -> {
                    modifiedReceipt.setReceiptId(receiptId);
                    return receiptJpaRepository.save(modifiedReceipt);
                });
    }

}

