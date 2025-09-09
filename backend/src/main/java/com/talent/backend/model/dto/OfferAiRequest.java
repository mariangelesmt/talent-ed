package com.talent.backend.model.dto;

import lombok.Data;

@Data
public class OfferAiRequest {
    private String position;
    private String description;
    private String previousQuestion;
    private String answer;
}
