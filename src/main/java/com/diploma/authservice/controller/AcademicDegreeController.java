package com.diploma.authservice.controller;

import com.diploma.authservice.dto.AcademicDegreeDTO;
import com.diploma.authservice.service.AcademicDegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/academic-degrees")
@RequiredArgsConstructor
public class AcademicDegreeController {

    private final AcademicDegreeService academicDegreeService;

    // CREATE
    @PostMapping
    public ResponseEntity<AcademicDegreeDTO> createAcademicDegree(
            @PathVariable Integer teacherId,
            @RequestBody AcademicDegreeDTO dto
    ) {
        AcademicDegreeDTO created = academicDegreeService.createAcademicDegreeForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // READ ALL by teacherId
    @GetMapping
    public ResponseEntity<List<AcademicDegreeDTO>> getAllDegreesByTeacher(@PathVariable Integer teacherId) {
        List<AcademicDegreeDTO> degrees = academicDegreeService.getAllAcademicDegreesByTeacher(teacherId);
        return ResponseEntity.ok(degrees);
    }

    // READ ONE by teacherId
    @GetMapping("/{degreeId}")
    public ResponseEntity<AcademicDegreeDTO> getAcademicDegreeByTeacher(
            @PathVariable Integer teacherId,
            @PathVariable Integer degreeId
    ) {
        AcademicDegreeDTO degree = academicDegreeService.getAcademicDegreeByTeacher(teacherId, degreeId);
        return ResponseEntity.ok(degree);
    }

    // UPDATE
    @PutMapping("/{degreeId}")
    public ResponseEntity<AcademicDegreeDTO> updateAcademicDegree(
            @PathVariable Integer teacherId,
            @PathVariable Integer degreeId,
            @RequestBody AcademicDegreeDTO dto
    ) {
        AcademicDegreeDTO updated = academicDegreeService.updateAcademicDegreeByTeacher(teacherId, degreeId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{degreeId}")
    public ResponseEntity<Void> deleteAcademicDegree(
            @PathVariable Integer teacherId,
            @PathVariable Integer degreeId
    ) {
        academicDegreeService.deleteAcademicDegreeByTeacher(teacherId, degreeId);
        return ResponseEntity.noContent().build();
    }
}

