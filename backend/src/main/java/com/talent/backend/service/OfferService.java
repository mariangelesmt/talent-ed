package com.talent.backend.service;

import com.talent.backend.model.Offer;
import com.talent.backend.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public Offer createOffer(Offer offer) {
        offer.setPublishedAt(LocalDateTime.now());
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Long id, Offer updatedOffer) {
        return offerRepository.findById(id).map(offer -> {
            offer.setTitle(updatedOffer.getTitle());
            offer.setCenter(updatedOffer.getCenter());
            offer.setDescription(updatedOffer.getDescription());
            offer.setForm(updatedOffer.getForm());
            return offerRepository.save(offer);
        }).orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}
