package com.diploma.authservice.controller;

import com.diploma.authservice.service.JobInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizationController {

    private final JobInfoService jobInfoService;

    @GetMapping
    public ResponseEntity<List<String>> getAllOrganizations() {
        List<String> organizations = jobInfoService.getAllDistinctOrganizations();
        return ResponseEntity.ok(organizations);
    }
}
