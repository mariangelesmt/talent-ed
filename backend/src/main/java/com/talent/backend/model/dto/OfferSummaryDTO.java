package com.talent.backend.model.dto;

import com.talent.backend.model.Center;
import com.talent.backend.model.ScheduleType;

import java.time.LocalDateTime;

public class OfferSummaryDTO {
    private Long id;
    private String title;
    private CenterDTO center;
    private String description;
    private LocalDateTime publishedAt;
    private ScheduleType schedule;

    public OfferSummaryDTO(Long id, String title, CenterDTO center, String description, LocalDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.center = center;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CenterDTO getCenter() {
        return center;
    }

    public void setCenter(CenterDTO center) {
        this.center = center;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

}
