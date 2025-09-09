package com.talent.backend.controller;

import com.talent.backend.model.*;
import com.talent.backend.model.ResponseStatus;
import com.talent.backend.model.dto.ResponseDTO;
import com.talent.backend.repository.FormRepository;
import com.talent.backend.repository.ResponseRepository;
import com.talent.backend.repository.UserRepository;
import com.talent.backend.service.ResponseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;
    private final UserRepository userRepository;
    private final FormRepository formRepository;
    private final ResponseRepository responseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> saveResponse(@RequestBody ResponseDTO responseDTO) {
        System.out.println("DTO recibido: " + responseDTO);

        // 1️⃣ Obtener usuario autenticado desde el token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (responseDTO.getForm() == null || responseDTO.getForm().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Form form = formRepository.findById(responseDTO.getForm().getId())
                .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));

        Response response = new Response();
        response.setUser(user);
        response.setForm(form);
        response.setSubmittedAt(responseDTO.getSubmittedAt());

        List<Answer> answers = responseDTO.getAnswers().stream().map(dto -> {
            FormQuestion aquestion = new FormQuestion();
            aquestion.setId(dto.getQuestionId());

            Answer answer = new Answer();
            answer.setQuestion(aquestion);
            answer.setAnswerText(dto.getAnswerText());
            answer.setResponse(response);
            return answer;
        }).toList();

        response.setAnswers(answers);

        responseService.saveResponse(response);

        return ResponseEntity.ok().body(Map.of("message", "Respuesta guardada correctamente"));
    }

    @GetMapping("/offer/{offerId}")
    public List<Response> getResponsesByOffer(@PathVariable Long offerId) {
        return responseRepository.findByFormOfferId(offerId);
    }

    @GetMapping
    public List<Response> getAllResponses() {
        return responseService.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String newStatus = body.get("status");

        Response response = responseService.findById(id)
                .orElseThrow(() -> new RuntimeException("Response not found"));

        response.setStatus(ResponseStatus.valueOf(newStatus));
        responseService.saveResponse(response);

        return ResponseEntity.ok(response);
    }

}
