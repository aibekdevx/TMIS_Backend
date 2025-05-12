package com.diploma.authservice.controller;

import com.diploma.authservice.service.AcademicDegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/degrees")
@RequiredArgsConstructor
public class DegreeTypeController {

    private final AcademicDegreeService academicDegreeService;

    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllDegreeTypes() {
        List<String> types = academicDegreeService.getAllDistinctDegreeTypes();
        return ResponseEntity.ok(types);
    }
}
