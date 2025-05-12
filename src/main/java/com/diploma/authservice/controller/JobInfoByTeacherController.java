package com.diploma.authservice.controller;

import com.diploma.authservice.dto.JobInfoDTO;
import com.diploma.authservice.service.JobInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/{teacherId}/jobinfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class JobInfoByTeacherController {

    private final JobInfoService jobInfoService;

    // CREATE
    @PostMapping
    public ResponseEntity<JobInfoDTO> createJobInfoForTeacher(
            @PathVariable Integer teacherId,
            @RequestBody JobInfoDTO dto
    ) {
        JobInfoDTO created = jobInfoService.createJobInfoForTeacher(teacherId, dto);
        return ResponseEntity.ok(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<JobInfoDTO>> getAllJobInfosByTeacher(@PathVariable Integer teacherId) {
        List<JobInfoDTO> jobInfos = jobInfoService.getAllJobInfosByTeacherId(teacherId);
        return ResponseEntity.ok(jobInfos);
    }

    // READ ONE
    @GetMapping("/{jobId}")
    public ResponseEntity<JobInfoDTO> getJobInfoByTeacher(
            @PathVariable Integer teacherId,
            @PathVariable Integer jobId
    ) {
        JobInfoDTO job = jobInfoService.getJobInfoByTeacher(teacherId, jobId);
        return ResponseEntity.ok(job);
    }

    // UPDATE
    @PutMapping("/{jobId}")
    public ResponseEntity<JobInfoDTO> updateJobInfoByTeacher(
            @PathVariable Integer teacherId,
            @PathVariable Integer jobId,
            @RequestBody JobInfoDTO dto
    ) {
        JobInfoDTO updated = jobInfoService.updateJobInfoByTeacher(teacherId, jobId, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobInfoByTeacher(
            @PathVariable Integer teacherId,
            @PathVariable Integer jobId
    ) {
        jobInfoService.deleteJobInfoByTeacher(teacherId, jobId);
        return ResponseEntity.noContent().build();
    }
}
