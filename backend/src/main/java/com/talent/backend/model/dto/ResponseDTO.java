package com.talent.backend.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDTO {
    private UserDTO user;
    private FormDTO form;
    private LocalDateTime submittedAt;
    private List<AnswerDTO> answers = new ArrayList<>();
}

