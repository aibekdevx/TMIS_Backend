package com.diploma.authservice.service;

import com.diploma.authservice.dto.AcademicTitleDTO;

import java.util.List;

public interface AcademicTitleService {

    AcademicTitleDTO createAcademicTitleForTeacher(Integer teacherId, AcademicTitleDTO dto);

    List<AcademicTitleDTO> getAllAcademicTitlesByTeacher(Integer teacherId);

    AcademicTitleDTO getAcademicTitleByTeacher(Integer teacherId, Integer titleId);

    AcademicTitleDTO updateAcademicTitleByTeacher(Integer teacherId, Integer titleId, AcademicTitleDTO dto);

    void deleteAcademicTitleByTeacher(Integer teacherId, Integer titleId);
}

