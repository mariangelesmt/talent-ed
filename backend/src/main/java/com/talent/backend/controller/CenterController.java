package com.talent.backend.controller;

import com.talent.backend.model.Center;
import com.talent.backend.service.CenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {

    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping
    public List<Center> getAllCenters() {
        return centerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(@PathVariable Long id) {
        return centerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Center createCenter(@RequestBody Center center) {
        return centerService.save(center);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable Long id, @RequestBody Center updatedCenter) {
        return centerService.findById(id)
                .map(center -> {
                    center.setName(updatedCenter.getName());
                    center.setLocation(updatedCenter.getLocation());
                    center.setDescription(updatedCenter.getDescription());
                    center.setImage(updatedCenter.getImage());
                    return ResponseEntity.ok(centerService.save(center));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        if (centerService.findById(id).isPresent()) {
            centerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
