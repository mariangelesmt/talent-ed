package com.talent.backend.controller;

import com.talent.backend.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping("/suggest")
    public ResponseEntity<String> getSuggestions(@RequestBody PromptRequest request) {
        String response = openAiService.getSuggestions(request.getPrompt());
        return ResponseEntity.ok(response);
    }

    public static class PromptRequest {
        private String prompt;

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }
}
