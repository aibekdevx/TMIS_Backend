package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.JobInfoDTO;
import com.diploma.authservice.entity.JobInfo;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.JobInfoRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.JobInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobInfoServiceImpl implements JobInfoService {

    private final JobInfoRepository jobInfoRepository;
    private final TeacherRepository teacherRepository;

    // ================= CREATE =================
    @Override
    public JobInfoDTO createJobInfoForTeacher(Integer teacherId, JobInfoDTO dto) {
        // Находим учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Создаём новую запись JobInfo
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobId(dto.getJobId());
        jobInfo.setTeacher(teacher);
        jobInfo.setOrganizationName(dto.getOrganizationName());
        jobInfo.setOrganizationAddress(dto.getOrganizationAddress());
        jobInfo.setPosition(dto.getPosition());
        jobInfo.setWorkExperience(dto.getWorkExperience());
        jobInfo.setMainWorkFlag(dto.getMainWorkFlag());
        jobInfo.setCreatedAt(dto.getCreatedAt());
        jobInfo.setUpdatedAt(dto.getUpdatedAt());

        JobInfo saved = jobInfoRepository.save(jobInfo);
        return mapToDTO(saved);
    }

    // ================= READ: GET ALL BY TEACHER =================
    @Override
    @Transactional(readOnly = true)
    public List<JobInfoDTO> getAllJobInfosByTeacherId(Integer teacherId) {
        List<JobInfo> jobInfos = jobInfoRepository.findByTeacher_TeacherId(teacherId);
        return jobInfos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ================= READ: GET ONE BY TEACHER =================
    @Override
    @Transactional(readOnly = true)
    public JobInfoDTO getJobInfoByTeacher(Integer teacherId, Integer jobId) {
        // Ищем jobInfo по jobId
        JobInfo job = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobInfo not found: " + jobId));

        // Проверяем, тот ли это учитель
        if (!job.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("JobInfo " + jobId + " does not belong to teacher " + teacherId);
        }
        return mapToDTO(job);
    }

    // ================= UPDATE =================
    @Override
    public JobInfoDTO updateJobInfoByTeacher(Integer teacherId, Integer jobId, JobInfoDTO dto) {
        JobInfo job = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobInfo not found: " + jobId));

        // Проверяем, тот ли это учитель
        if (!job.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("JobInfo " + jobId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля
        // Смена teacherId не разрешена, но при необходимости можно дать такую опцию
        job.setOrganizationName(dto.getOrganizationName());
        job.setOrganizationAddress(dto.getOrganizationAddress());
        job.setPosition(dto.getPosition());
        job.setWorkExperience(dto.getWorkExperience());
        job.setMainWorkFlag(dto.getMainWorkFlag());
        job.setCreatedAt(dto.getCreatedAt());
        job.setUpdatedAt(dto.getUpdatedAt());

        JobInfo updated = jobInfoRepository.save(job);
        return mapToDTO(updated);
    }

    // ================= DELETE =================
    @Override
    public void deleteJobInfoByTeacher(Integer teacherId, Integer jobId) {
        JobInfo job = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobInfo not found: " + jobId));

        // Проверяем принадлежность
        if (!job.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("JobInfo " + jobId + " does not belong to teacher " + teacherId);
        }

        jobInfoRepository.delete(job);
    }

    // ================= MAP ENTITY <-> DTO =================
    private JobInfoDTO mapToDTO(JobInfo job) {
        JobInfoDTO dto = new JobInfoDTO();
        dto.setJobId(job.getJobId());
        dto.setTeacherId(job.getTeacher().getTeacherId());
        dto.setOrganizationName(job.getOrganizationName());
        dto.setOrganizationAddress(job.getOrganizationAddress());
        dto.setPosition(job.getPosition());
        dto.setWorkExperience(job.getWorkExperience());
        dto.setMainWorkFlag(job.getMainWorkFlag());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setUpdatedAt(job.getUpdatedAt());
        return dto;
    }
}

