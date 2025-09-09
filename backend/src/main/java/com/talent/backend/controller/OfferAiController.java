package com.talent.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.backend.model.OpenAiResponse;
import com.talent.backend.model.dto.OfferAiRequest;
import com.talent.backend.service.OfferAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class OfferAiController {

    private final OfferAiService offerAiService;

    @PostMapping("/suggest-question")
    public ResponseEntity<Map<String, Object>> suggestQuestion(@RequestBody OfferAiRequest request) {
        OpenAiResponse.Message message = offerAiService.suggestQuestion(request);

        // Devolver el content como JSON
        Map<String, Object> body = Map.of(
                "role", message.getRole(),
                "content", message.getContent()
        );
        return ResponseEntity.ok(body);
    }
}

