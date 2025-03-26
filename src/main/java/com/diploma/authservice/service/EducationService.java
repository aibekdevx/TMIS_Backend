package com.diploma.authservice.service;

import com.diploma.authservice.dto.EducationDTO;

import java.util.List;

public interface EducationService {

    EducationDTO createEducationForTeacher(Integer teacherId, EducationDTO dto);

    List<EducationDTO> getAllEducationsByTeacher(Integer teacherId);

    EducationDTO getEducationByTeacher(Integer teacherId, Integer educationId);

    EducationDTO updateEducationByTeacher(Integer teacherId, Integer educationId, EducationDTO dto);

    void deleteEducationByTeacher(Integer teacherId, Integer educationId);
}

