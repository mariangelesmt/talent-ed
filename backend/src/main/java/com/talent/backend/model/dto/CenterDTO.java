package com.talent.backend.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String image;
}
