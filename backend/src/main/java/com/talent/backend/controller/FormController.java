package com.talent.backend.controller;

import com.talent.backend.model.Form;
import com.talent.backend.model.dto.FormDTO;
import com.talent.backend.model.dto.FormDetailDTO;
import com.talent.backend.service.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/by-offer/{offerId}")
    public FormDetailDTO getFormByOfferId(@PathVariable Long offerId) {
        return formService.getFormByOfferId(offerId);
    }

    @PostMapping
    public Form createForm(@RequestBody FormDTO form) {
        return formService.createForm(form);
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }
}

