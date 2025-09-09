package com.talent.backend.model.dto;

import java.util.List;

public class FormDetailDTO {
    private Long formId;
    private String title;
    private String description;
    private List<FormQuestionDTO> questions;

    public FormDetailDTO() {}

    public FormDetailDTO(Long formId, String title, String description, List<FormQuestionDTO> questions) {
        this.formId = formId;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public Long getFormId() {
        return formId;
    }
    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<FormQuestionDTO> getQuestions() {
        return questions;
    }
    public void setQuestions(List<FormQuestionDTO> questions) {
        this.questions = questions;
    }
}
