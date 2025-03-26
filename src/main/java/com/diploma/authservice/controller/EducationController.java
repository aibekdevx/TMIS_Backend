package com.diploma.authservice.controller;

import com.diploma.authservice.dto.EducationDTO;
import com.diploma.authservice.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    // CREATE
    @PostMapping
    public ResponseEntity<EducationDTO> createEducation(
            @PathVariable Integer teacherId,
            @RequestBody EducationDTO dto
    ) {
        EducationDTO created = educationService.createEducationForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // GET ALL BY TEACHER
    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducationsByTeacher(@PathVariable Integer teacherId) {
        List<EducationDTO> educations = educationService.getAllEducationsByTeacher(teacherId);
        return ResponseEntity.ok(educations);
    }

    // GET ONE
    @GetMapping("/{educationId}")
    public ResponseEntity<EducationDTO> getEducation(
            @PathVariable Integer teacherId,
            @PathVariable Integer educationId
    ) {
        EducationDTO education = educationService.getEducationByTeacher(teacherId, educationId);
        return ResponseEntity.ok(education);
    }

    // UPDATE
    @PutMapping("/{educationId}")
    public ResponseEntity<EducationDTO> updateEducation(
            @PathVariable Integer teacherId,
            @PathVariable Integer educationId,
            @RequestBody EducationDTO dto
    ) {
        EducationDTO updated = educationService.updateEducationByTeacher(teacherId, educationId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{educationId}")
    public ResponseEntity<Void> deleteEducation(
            @PathVariable Integer teacherId,
            @PathVariable Integer educationId
    ) {
        educationService.deleteEducationByTeacher(teacherId, educationId);
        return ResponseEntity.noContent().build();
    }
}

