package com.talent.backend.service;

import com.talent.backend.model.dto.OfferAiRequest;
import com.talent.backend.model.OpenAiRequest;
import com.talent.backend.model.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferAiService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public OpenAiResponse.Message suggestQuestion(OfferAiRequest request) {
        String prompt = String.format(
                "Eres un sistema que ayuda a crear formularios para seleccionar profesores. "
                        + "Estamos diseñando un formulario para el puesto de '%s' con la descripción '%s'. "
                        + "La pregunta anterior fue: '%s' y la respuesta del candidato fue: '%s'. "
                        + "Sugiere la siguiente pregunta adecuada.Responde únicamente con la pregunta, sin explicaciones ni introducciones",
                request.getPosition(),
                request.getDescription(),
                request.getPreviousQuestion(),
                request.getAnswer()
        );

        OpenAiRequest.Message message = new OpenAiRequest.Message();
        message.setRole("user");
        message.setContent(prompt);

        OpenAiRequest openAiRequest = new OpenAiRequest();
        openAiRequest.setMessages(List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(openAiRequest, headers);

        ResponseEntity<OpenAiResponse> response = restTemplate.postForEntity(
                apiUrl,
                entity,
                OpenAiResponse.class
        );

        return response.getBody().getChoices().get(0).getMessage();
    }
}
