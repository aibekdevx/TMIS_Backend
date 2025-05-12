package com.diploma.authservice.service;

import com.diploma.authservice.dto.TeacherDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Integer teacherId);

    TeacherDTO updateTeacher(Integer teacherId, TeacherDTO teacherDTO);

    void deleteTeacher(Integer teacherId);

    long getTotalCount();

    @Transactional(readOnly = true)
    List<TeacherDTO> getTeachersWithFilters(String search, String organization, String degree, int page, int size);
}

