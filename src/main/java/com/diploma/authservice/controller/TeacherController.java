package com.diploma.authservice.controller;

import com.diploma.authservice.dto.TeacherDTO;
import com.diploma.authservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO created = teacherService.createTeacher(teacherDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Integer teacherId) {
        TeacherDTO dto = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getTeachersWithFilters(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String organization,
            @RequestParam(required = false) String degree,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        List<TeacherDTO> filtered = teacherService.getTeachersWithFilters(search, organization, degree, page, size);
        return ResponseEntity.ok(filtered);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") Integer teacherId,
                                                    @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updated = teacherService.updateTeacher(teacherId, teacherDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalTeachersCount() {
        long count = teacherService.getTotalCount();
        return ResponseEntity.ok(count);
    }

}
