package pb.wi.kck.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pb.wi.kck.exceptions.DealNotFoundException;
import pb.wi.kck.model.Deal;
import pb.wi.kck.repositories.DealJpaRepository;

import java.util.List;


//TODO: mapping do dto
@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealJpaRepository dealJpaRepository;

    DealController(DealJpaRepository dealJpaRepository) {
        this.dealJpaRepository = dealJpaRepository;
    }

    @GetMapping()
    List<Deal> getAll() {
        return dealJpaRepository.findAll();
    }

    @PostMapping("/new")
    Deal newDeal(@RequestBody Deal newDeal) {
        return dealJpaRepository.save(newDeal);
    }

    @GetMapping(value = "/{dealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Deal getDeal(@PathVariable Integer dealId) {
        return dealJpaRepository.findById(dealId)
                .orElseThrow(() -> new DealNotFoundException(dealId));
    }

    @DeleteMapping(value = "/{dealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDeal(@PathVariable Integer dealId) {
        dealJpaRepository.deleteById(dealId);
    }

    /*
    @PutMapping(value = "/{dealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Deal modifyDeal(@PathVariable Integer dealId, @RequestBody Deal modifiedDeal) {
        return dealJpaRepository.findById(dealId)
                .map(d -> {
                    d.setDealId(modifiedDeal.getDealId());
                    d.setCompany(modifiedDeal.getCompany());
                    d.setUser(modifiedDeal.getUser());
                    d.setDocumentName(modifiedDeal.getDocumentName());
                    d.setPurchaseDate(modifiedDeal.getPurchaseDate());
                    d.setPurchaseValue(modifiedDeal.getPurchaseValue());
                    return dealJpaRepository.save(d);
                })
                .orElseGet(() -> {
                    modifiedDeal.setDealId(dealId);
                    return dealJpaRepository.save(modifiedDeal);
                });
    }
    */

}

