package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.EducationDTO;
import com.diploma.authservice.entity.Education;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.EducationRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public EducationDTO createEducationForTeacher(Integer teacherId, EducationDTO dto) {
        // Проверяем наличие учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Создаём новую запись Education
        Education education = new Education();
        education.setEducationId(dto.getEducationId()); // Если генерируется автоматически, уберите
        education.setTeacher(teacher);
        education.setInstitutionName(dto.getInstitutionName());
        education.setSpecialty(dto.getSpecialty());
        education.setDiplomaQualification(dto.getDiplomaQualification());
        education.setStartYear(dto.getStartYear());
        education.setEndYear(dto.getEndYear());
        education.setCertificateNumber(dto.getCertificateNumber());
        education.setCreatedAt(dto.getCreatedAt());
        education.setUpdatedAt(dto.getUpdatedAt());

        Education saved = educationRepository.save(education);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EducationDTO> getAllEducationsByTeacher(Integer teacherId) {
        List<Education> educations = educationRepository.findByTeacher_TeacherId(teacherId);
        return educations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EducationDTO getEducationByTeacher(Integer teacherId, Integer educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found: " + educationId));

        // Проверяем, принадлежит ли запись этому учителю
        if (!education.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("Education " + educationId + " does not belong to teacher " + teacherId);
        }

        return mapToDTO(education);
    }

    @Override
    public EducationDTO updateEducationByTeacher(Integer teacherId, Integer educationId, EducationDTO dto) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found: " + educationId));

        // Проверяем принадлежность учителю
        if (!education.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("Education " + educationId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля (teacherId менять не даём)
        education.setInstitutionName(dto.getInstitutionName());
        education.setSpecialty(dto.getSpecialty());
        education.setDiplomaQualification(dto.getDiplomaQualification());
        education.setStartYear(dto.getStartYear());
        education.setEndYear(dto.getEndYear());
        education.setCertificateNumber(dto.getCertificateNumber());
        education.setCreatedAt(dto.getCreatedAt());
        education.setUpdatedAt(dto.getUpdatedAt());

        Education updated = educationRepository.save(education);
        return mapToDTO(updated);
    }

    @Override
    public void deleteEducationByTeacher(Integer teacherId, Integer educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found: " + educationId));

        if (!education.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("Education " + educationId + " does not belong to teacher " + teacherId);
        }

        educationRepository.delete(education);
    }

    // =========================================
    // MAP ENTITY -> DTO
    // =========================================
    private EducationDTO mapToDTO(Education education) {
        EducationDTO dto = new EducationDTO();
        dto.setEducationId(education.getEducationId());
        dto.setTeacherId(education.getTeacher().getTeacherId());
        dto.setInstitutionName(education.getInstitutionName());
        dto.setSpecialty(education.getSpecialty());
        dto.setDiplomaQualification(education.getDiplomaQualification());
        dto.setStartYear(education.getStartYear());
        dto.setEndYear(education.getEndYear());
        dto.setCertificateNumber(education.getCertificateNumber());
        dto.setCreatedAt(education.getCreatedAt());
        dto.setUpdatedAt(education.getUpdatedAt());
        return dto;
    }
}

