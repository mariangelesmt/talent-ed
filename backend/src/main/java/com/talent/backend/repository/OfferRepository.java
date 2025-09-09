package com.talent.backend.repository;

import com.talent.backend.model.Offer;
import com.talent.backend.model.dto.OfferSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT new com.talent.backend.model.dto.OfferSummaryDTO(o.id, o.title, new com.talent.backend.model.dto.CenterDTO(c.id, c.name, c.location, c.description, c.image), o.description, o.publishedAt) FROM Offer o LEFT JOIN o.center c")
    List<OfferSummaryDTO> findAllOffersSummary();

    @Query("""
       SELECT new com.talent.backend.model.dto.OfferSummaryDTO(
           o.id, o.title, new com.talent.backend.model.dto.CenterDTO(
                                   c.id, c.name, c.location, c.description, c.image
                               ),
                               o.description,
                               o.publishedAt
                           )
                           FROM Offer o
                           LEFT JOIN o.center c
                           LEFT JOIN o.form f
                           WHERE o.id = :id
       """)
    Optional<OfferSummaryDTO> findOfferDetailById(@Param("id") Long id);
}
