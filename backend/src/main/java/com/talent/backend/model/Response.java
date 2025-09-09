package com.talent.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"responses"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    @JsonIgnoreProperties({"offer", "questions"})
    private Form form;

    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"response"})
    private List<Answer> answers;

    @Enumerated(EnumType.STRING)
    private ResponseStatus status = ResponseStatus.REVISION;
}

