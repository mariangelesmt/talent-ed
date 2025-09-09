package com.talent.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "form_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String type;

    private boolean openaiTrigger = false;

    private Long originIaQuestionId;

    private boolean generatedByIa = false;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference
    private Form form;


    public FormQuestion(String text, String type, boolean openaiTrigger, Long originIaQuestionId, boolean generatedByIa, Form form) {
        this.text = text;
        this.type = type;
        this.openaiTrigger = openaiTrigger;
        this.originIaQuestionId = originIaQuestionId;
        this.generatedByIa = generatedByIa;
        this.form = form;
    }


}

