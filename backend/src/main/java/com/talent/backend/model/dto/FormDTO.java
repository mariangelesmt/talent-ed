package com.talent.backend.model.dto;
import lombok.Data;

import java.util.List;

@Data
public class FormDTO {
    private Long id;
    private String title;
    private String description;
    private Long offerId;
    private List<FormQuestionDTO> questions;
}
