package com.diploma.authservice.controller;

import com.diploma.authservice.dto.InclusiveEducationDTO;
import com.diploma.authservice.service.InclusiveEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/inclusive-educations")
@RequiredArgsConstructor
public class InclusiveEducationController {

    private final InclusiveEducationService inclusiveEducationService;

    // CREATE
    @PostMapping
    public ResponseEntity<InclusiveEducationDTO> createInclusiveEducation(
            @PathVariable Integer teacherId,
            @RequestBody InclusiveEducationDTO dto
    ) {
        InclusiveEducationDTO created = inclusiveEducationService.createInclusiveEducationForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<InclusiveEducationDTO>> getAllInclusiveEducations(
            @PathVariable Integer teacherId
    ) {
        List<InclusiveEducationDTO> list = inclusiveEducationService.getAllInclusiveEducationsByTeacher(teacherId);
        return ResponseEntity.ok(list);
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<InclusiveEducationDTO> getInclusiveEducation(
            @PathVariable Integer teacherId,
            @PathVariable("id") Integer inclusiveEducationId
    ) {
        InclusiveEducationDTO dto = inclusiveEducationService.getInclusiveEducationByTeacher(teacherId, inclusiveEducationId);
        return ResponseEntity.ok(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<InclusiveEducationDTO> updateInclusiveEducation(
            @PathVariable Integer teacherId,
            @PathVariable("id") Integer inclusiveEducationId,
            @RequestBody InclusiveEducationDTO dto
    ) {
        InclusiveEducationDTO updated = inclusiveEducationService.updateInclusiveEducationByTeacher(
                teacherId, inclusiveEducationId, dto
        );
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInclusiveEducation(
            @PathVariable Integer teacherId,
            @PathVariable("id") Integer inclusiveEducationId
    ) {
        inclusiveEducationService.deleteInclusiveEducationByTeacher(teacherId, inclusiveEducationId);
        return ResponseEntity.noContent().build();
    }
}

