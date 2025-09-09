package com.talent.backend.service;

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
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSuggestions(String userPrompt) {
        OpenAiRequest.Message message = new OpenAiRequest.Message();
        message.setRole("user");
        message.setContent(userPrompt);

        OpenAiRequest request = new OpenAiRequest();
        request.setMessages(List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<OpenAiRequest> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<OpenAiResponse> response = restTemplate.postForEntity(
                apiUrl,
                httpEntity,
                OpenAiResponse.class
        );

        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}
