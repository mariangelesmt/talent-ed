package com.talent.backend.service;

import com.talent.backend.model.Form;
import com.talent.backend.model.FormQuestion;
import com.talent.backend.model.Offer;
import com.talent.backend.model.dto.FormDTO;
import com.talent.backend.model.dto.FormDetailDTO;
import com.talent.backend.model.dto.FormQuestionDTO;
import com.talent.backend.repository.FormRepository;
import com.talent.backend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    private final FormRepository formRepository;

    @Autowired
    private OfferRepository offerRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form createForm(FormDTO dto) {
        Form form = new Form();
        form.setTitle(dto.getTitle());
        form.setDescription(dto.getDescription());

        // ⚡ Asociar la oferta
        Offer offer = offerRepository.findById(dto.getOfferId())
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
        form.setOffer(offer);

        // Mapear preguntas
        List<FormQuestion> questions = dto.getQuestions().stream().map(qdto -> {
            FormQuestion q = new FormQuestion();
            q.setText(qdto.getText());
            q.setType(qdto.getType());
            q.setOpenaiTrigger(qdto.isOpenaiTrigger());
            q.setGeneratedByIa(qdto.isGeneratedByIa());
            q.setForm(form); // asociación bidireccional
            return q;
        }).toList();

        form.setQuestions(questions);

        return formRepository.save(form);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public FormDetailDTO getFormByOfferId(Long offerId) {
        List<Form> forms = formRepository.findByOfferId(offerId);
        Form form = forms.isEmpty() ? null : forms.getFirst();

        if (form == null) {
            throw new RuntimeException("No existe formulario para esta oferta");
        }

        return new FormDetailDTO(
                form.getId(),
                form.getTitle(),
                form.getDescription(),
                form.getQuestions().stream()
                        .map(q -> new FormQuestionDTO(
                                q.getId(),
                                q.getText(),
                                q.getType(),
                                q.isOpenaiTrigger()
                        ))
                        .collect(Collectors.toList())
        );
    }

}
