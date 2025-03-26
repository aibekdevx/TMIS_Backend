package com.diploma.authservice.service;

import com.diploma.authservice.dto.InclusiveEducationDTO;

import java.util.List;

public interface InclusiveEducationService {

    InclusiveEducationDTO createInclusiveEducationForTeacher(Integer teacherId, InclusiveEducationDTO dto);

    List<InclusiveEducationDTO> getAllInclusiveEducationsByTeacher(Integer teacherId);

    InclusiveEducationDTO getInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId);

    InclusiveEducationDTO updateInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId, InclusiveEducationDTO dto);

    void deleteInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId);
}

