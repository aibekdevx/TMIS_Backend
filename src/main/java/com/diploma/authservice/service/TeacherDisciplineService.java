package com.diploma.authservice.service;

import com.diploma.authservice.dto.TeacherDisciplineDTO;
import com.diploma.authservice.entity.Discipline;

import java.util.List;

public interface TeacherDisciplineService {

    // Добавить дисциплину учителю
    TeacherDisciplineDTO addDisciplineToTeacher(Integer teacherId, TeacherDisciplineDTO dto);

    // Получить все дисциплины конкретного учителя
    List<TeacherDisciplineDTO> getAllDisciplinesByTeacher(Integer teacherId);

    // Получить одну связь (Teacher <-> Discipline)
    TeacherDisciplineDTO getTeacherDiscipline(Integer teacherId, Integer disciplineId);

    // Обновить (например, обновить timestamps) связь
    TeacherDisciplineDTO updateTeacherDiscipline(Integer teacherId, Integer disciplineId, TeacherDisciplineDTO dto);

    // Удалить дисциплину у учителя
    void removeTeacherDiscipline(Integer teacherId, Integer disciplineId);

    List<TeacherDisciplineDTO> getAllByDisciplineId(Integer disciplineId);
}
