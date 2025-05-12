package com.diploma.authservice.repository;

import com.diploma.authservice.entity.TeacherDiscipline;
import com.diploma.authservice.entity.TeacherDisciplineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDisciplineRepository
        extends JpaRepository<TeacherDiscipline, TeacherDisciplineId> {

    // Получить все дисциплины, связанные с конкретным учителем
    List<TeacherDiscipline> findByTeacher_TeacherId(Integer teacherId);

    // Получить все записи TeacherDiscipline по ID дисциплины
    List<TeacherDiscipline> findByDiscipline_DisciplineId(Integer disciplineId);

    // Удалён метод findAllByDisciplineId, т.к. он конфликтовал с названием поля в сущности
}
