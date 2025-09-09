package com.talent.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;


@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(length = 2000)
    private String description;

    private LocalDateTime publishedAt;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"offer"})
    private Form form;


    @ElementCollection
    @CollectionTable(name = "offer_requirements", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "requirement")
    private List<String> requirements;

    @Enumerated(EnumType.STRING)
    private ScheduleType schedule;
}
