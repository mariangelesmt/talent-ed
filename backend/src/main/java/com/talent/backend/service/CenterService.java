package com.talent.backend.service;

import com.talent.backend.model.Center;
import com.talent.backend.repository.CenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterService {

    private final CenterRepository centerRepository;

    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public List<Center> findAll() {
        return centerRepository.findAll();
    }

    public Optional<Center> findById(Long id) {
        return centerRepository.findById(id);
    }

    public Center save(Center center) {
        return centerRepository.save(center);
    }

    public void deleteById(Long id) {
        centerRepository.deleteById(id);
    }
}
