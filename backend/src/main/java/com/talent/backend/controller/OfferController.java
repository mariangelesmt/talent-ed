package com.talent.backend.controller;

import com.talent.backend.model.Offer;
import com.talent.backend.model.dto.OfferSummaryDTO;
import com.talent.backend.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.talent.backend.repository.OfferRepository;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final OfferRepository offerRepository;

    public OfferController(OfferService offerService, OfferRepository offerRepository) {
        this.offerService = offerService;
        this.offerRepository = offerRepository;
    }

    @GetMapping
    public List<OfferSummaryDTO> getAllOffers() {
        return offerRepository.findAllOffersSummary();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OfferSummaryDTO> getOfferById(@PathVariable Long id) {
        return offerRepository.findOfferDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer savedOffer = offerService.createOffer(offer);
        return ResponseEntity.ok(savedOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer offer) {
        try {
            Offer updated = offerService.updateOffer(id, offer);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
