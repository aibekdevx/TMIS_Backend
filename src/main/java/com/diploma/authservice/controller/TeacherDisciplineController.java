package com.diploma.authservice.controller;

import com.diploma.authservice.dto.TeacherDisciplineDTO;
import com.diploma.authservice.service.TeacherDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/disciplines")
@RequiredArgsConstructor
public class TeacherDisciplineController {

    private final TeacherDisciplineService teacherDisciplineService;

    // CREATE / ADD
    @PostMapping
    public ResponseEntity<TeacherDisciplineDTO> addDisciplineToTeacher(
            @PathVariable Integer teacherId,
            @RequestBody TeacherDisciplineDTO dto
    ) {
        // В теле запроса передаём disciplineId, createdAt, updatedAt
        TeacherDisciplineDTO created = teacherDisciplineService.addDisciplineToTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // GET ALL (disciplines for a teacher)
    @GetMapping
    public ResponseEntity<List<TeacherDisciplineDTO>> getAllDisciplinesByTeacher(
            @PathVariable Integer teacherId
    ) {
        List<TeacherDisciplineDTO> list = teacherDisciplineService.getAllDisciplinesByTeacher(teacherId);
        return ResponseEntity.ok(list);
    }

    // GET ONE
    @GetMapping("/{disciplineId}")
    public ResponseEntity<TeacherDisciplineDTO> getTeacherDiscipline(
            @PathVariable Integer teacherId,
            @PathVariable Integer disciplineId
    ) {
        TeacherDisciplineDTO dto = teacherDisciplineService.getTeacherDiscipline(teacherId, disciplineId);
        return ResponseEntity.ok(dto);
    }

    // UPDATE
    @PutMapping("/{disciplineId}")
    public ResponseEntity<TeacherDisciplineDTO> updateTeacherDiscipline(
            @PathVariable Integer teacherId,
            @PathVariable Integer disciplineId,
            @RequestBody TeacherDisciplineDTO dto
    ) {
        TeacherDisciplineDTO updated = teacherDisciplineService.updateTeacherDiscipline(teacherId, disciplineId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{disciplineId}")
    public ResponseEntity<Void> removeTeacherDiscipline(
            @PathVariable Integer teacherId,
            @PathVariable Integer disciplineId
    ) {
        teacherDisciplineService.removeTeacherDiscipline(teacherId, disciplineId);
        return ResponseEntity.noContent().build();
    }
}

