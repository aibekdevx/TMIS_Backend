package com.diploma.authservice.controller;

import com.diploma.authservice.dto.ForeignCognitionDTO;
import com.diploma.authservice.service.ForeignCognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/foreign-cognitions")
@RequiredArgsConstructor
public class ForeignCognitionController {

    private final ForeignCognitionService foreignCognitionService;

    // CREATE
    @PostMapping
    public ResponseEntity<ForeignCognitionDTO> createForeignCognition(
            @PathVariable Integer teacherId,
            @RequestBody ForeignCognitionDTO dto
    ) {
        ForeignCognitionDTO created = foreignCognitionService.createForeignCognitionForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // GET ALL by teacher
    @GetMapping
    public ResponseEntity<List<ForeignCognitionDTO>> getAllForeignCognitions(
            @PathVariable Integer teacherId
    ) {
        List<ForeignCognitionDTO> list = foreignCognitionService.getAllForeignCognitionsByTeacher(teacherId);
        return ResponseEntity.ok(list);
    }

    // GET ONE
    @GetMapping("/{recognitionId}")
    public ResponseEntity<ForeignCognitionDTO> getForeignCognition(
            @PathVariable Integer teacherId,
            @PathVariable Integer recognitionId
    ) {
        ForeignCognitionDTO dto = foreignCognitionService.getForeignCognitionByTeacher(teacherId, recognitionId);
        return ResponseEntity.ok(dto);
    }

    // UPDATE
    @PutMapping("/{recognitionId}")
    public ResponseEntity<ForeignCognitionDTO> updateForeignCognition(
            @PathVariable Integer teacherId,
            @PathVariable Integer recognitionId,
            @RequestBody ForeignCognitionDTO dto
    ) {
        ForeignCognitionDTO updated = foreignCognitionService.updateForeignCognitionByTeacher(teacherId, recognitionId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{recognitionId}")
    public ResponseEntity<Void> deleteForeignCognition(
            @PathVariable Integer teacherId,
            @PathVariable Integer recognitionId
    ) {
        foreignCognitionService.deleteForeignCognitionByTeacher(teacherId, recognitionId);
        return ResponseEntity.noContent().build();
    }
}

