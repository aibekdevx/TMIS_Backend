package com.diploma.authservice.service;

import com.diploma.authservice.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Integer teacherId);

    List<TeacherDTO> getAllTeachers();

    TeacherDTO updateTeacher(Integer teacherId, TeacherDTO teacherDTO);

    void deleteTeacher(Integer teacherId);

}

