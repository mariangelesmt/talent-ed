package com.talent.backend.service;

import com.talent.backend.model.Center;
import com.talent.backend.model.Response;
import com.talent.backend.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;

    @Transactional
    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    public List<Response> findAll() {
        return responseRepository.findAll();
    }

    public Optional<Response> findById(Long id) {
        return responseRepository.findById(id);
    }
}
