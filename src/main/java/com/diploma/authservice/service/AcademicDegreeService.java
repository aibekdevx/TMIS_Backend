package com.diploma.authservice.service;

import com.diploma.authservice.dto.AcademicDegreeDTO;

import java.util.List;

public interface AcademicDegreeService {

    // Создать AcademicDegree, привязанную к Teacher
    AcademicDegreeDTO createAcademicDegreeForTeacher(Integer teacherId, AcademicDegreeDTO dto);

    // Получить список AcademicDegree по teacherId
    List<AcademicDegreeDTO> getAllAcademicDegreesByTeacher(Integer teacherId);

    List<String> getAllDistinctDegreeTypes();

    // Получить одну запись AcademicDegree по ее ID, но в контексте teacherId
    AcademicDegreeDTO getAcademicDegreeByTeacher(Integer teacherId, Integer degreeId);

    // Обновить AcademicDegree
    AcademicDegreeDTO updateAcademicDegreeByTeacher(Integer teacherId, Integer degreeId, AcademicDegreeDTO dto);

    // Удалить AcademicDegree
    void deleteAcademicDegreeByTeacher(Integer teacherId, Integer degreeId);
}

