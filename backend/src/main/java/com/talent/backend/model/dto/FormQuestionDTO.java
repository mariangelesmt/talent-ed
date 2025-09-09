package com.talent.backend.model.dto;

public class FormQuestionDTO {
    private Long id;
    private String text;
    private String type;
    private boolean openaiTrigger;
    private boolean generatedByIa;

    public FormQuestionDTO() {}

    public FormQuestionDTO(Long id, String text, String type, boolean openaiTrigger) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.openaiTrigger = openaiTrigger;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isOpenaiTrigger() {
        return openaiTrigger;
    }
    public void setOpenaiTrigger(boolean generatedByIa) {
        this.openaiTrigger = openaiTrigger;
    }

    public boolean isGeneratedByIa() {
        return generatedByIa;
    }

    public void setGeneratedByIa(boolean generatedByIa) {
        this.generatedByIa = generatedByIa;
    }

}
