package com.diploma.authservice.service;

import com.diploma.authservice.dto.ForeignCognitionDTO;

import java.util.List;

public interface ForeignCognitionService {

    ForeignCognitionDTO createForeignCognitionForTeacher(Integer teacherId, ForeignCognitionDTO dto);

    List<ForeignCognitionDTO> getAllForeignCognitionsByTeacher(Integer teacherId);

    ForeignCognitionDTO getForeignCognitionByTeacher(Integer teacherId, Integer recognitionId);

    ForeignCognitionDTO updateForeignCognitionByTeacher(Integer teacherId, Integer recognitionId, ForeignCognitionDTO dto);

    void deleteForeignCognitionByTeacher(Integer teacherId, Integer recognitionId);
}

