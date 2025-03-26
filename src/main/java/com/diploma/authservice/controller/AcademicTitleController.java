package com.diploma.authservice.controller;

import com.diploma.authservice.dto.AcademicTitleDTO;
import com.diploma.authservice.service.AcademicTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/academic-titles")
@RequiredArgsConstructor
public class AcademicTitleController {

    private final AcademicTitleService academicTitleService;

    // CREATE
    @PostMapping
    public ResponseEntity<AcademicTitleDTO> createAcademicTitle(
            @PathVariable Integer teacherId,
            @RequestBody AcademicTitleDTO dto
    ) {
        AcademicTitleDTO created = academicTitleService.createAcademicTitleForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AcademicTitleDTO>> getAllAcademicTitles(
            @PathVariable Integer teacherId
    ) {
        List<AcademicTitleDTO> titles = academicTitleService.getAllAcademicTitlesByTeacher(teacherId);
        return ResponseEntity.ok(titles);
    }

    // GET ONE
    @GetMapping("/{titleId}")
    public ResponseEntity<AcademicTitleDTO> getAcademicTitle(
            @PathVariable Integer teacherId,
            @PathVariable Integer titleId
    ) {
        AcademicTitleDTO title = academicTitleService.getAcademicTitleByTeacher(teacherId, titleId);
        return ResponseEntity.ok(title);
    }

    // UPDATE
    @PutMapping("/{titleId}")
    public ResponseEntity<AcademicTitleDTO> updateAcademicTitle(
            @PathVariable Integer teacherId,
            @PathVariable Integer titleId,
            @RequestBody AcademicTitleDTO dto
    ) {
        AcademicTitleDTO updated = academicTitleService.updateAcademicTitleByTeacher(teacherId, titleId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{titleId}")
    public ResponseEntity<Void> deleteAcademicTitle(
            @PathVariable Integer teacherId,
            @PathVariable Integer titleId
    ) {
        academicTitleService.deleteAcademicTitleByTeacher(teacherId, titleId);
        return ResponseEntity.noContent().build();
    }
}

