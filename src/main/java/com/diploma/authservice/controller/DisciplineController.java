// src/main/java/com/diploma/authservice/controller/DisciplineController.java
package com.diploma.authservice.controller;

import com.diploma.authservice.dto.DisciplineDTO;
import com.diploma.authservice.dto.TeacherDisciplineDTO;
import com.diploma.authservice.service.DisciplineService;
import com.diploma.authservice.service.TeacherDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplines")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DisciplineController {

    private final DisciplineService service;
    private final TeacherDisciplineService teacherDisciplineService;

    /* GET /api/v1/disciplines */
    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /* POST /api/v1/disciplines */
    @PostMapping
    public ResponseEntity<DisciplineDTO> create(@RequestBody DisciplineDTO dto) {
        DisciplineDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /* PUT /api/v1/disciplines/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDTO> update(@PathVariable Integer id,
                                                @RequestBody DisciplineDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    /* DELETE /api/v1/disciplines/{id} */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/{disciplineId}/teachers")
    public ResponseEntity<List<TeacherDisciplineDTO>> getTeachersByDiscipline(
            @PathVariable Integer disciplineId
    ) {
        List<TeacherDisciplineDTO> list =
                teacherDisciplineService.getAllByDisciplineId(disciplineId);
        return ResponseEntity.ok(list);
    }
}
