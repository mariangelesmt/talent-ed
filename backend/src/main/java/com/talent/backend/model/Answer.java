package com.talent.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnoreProperties({"answers"})
    private Response response;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private FormQuestion question;

    @Column(columnDefinition = "TEXT")
    private String answerText;
}
