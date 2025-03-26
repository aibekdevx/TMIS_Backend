package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.TeacherDisciplineDTO;
import com.diploma.authservice.entity.Discipline;
import com.diploma.authservice.entity.TeacherDiscipline;
import com.diploma.authservice.entity.TeacherDisciplineId;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.DisciplineRepository;
import com.diploma.authservice.repository.TeacherDisciplineRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.TeacherDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherDisciplineServiceImpl implements TeacherDisciplineService {

    private final TeacherDisciplineRepository teacherDisciplineRepository;
    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;

    @Override
    public TeacherDisciplineDTO addDisciplineToTeacher(Integer teacherId, TeacherDisciplineDTO dto) {
        // Ищем учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Ищем дисциплину
        Discipline discipline = disciplineRepository.findById(dto.getDisciplineId())
                .orElseThrow(() -> new RuntimeException("Discipline not found: " + dto.getDisciplineId()));

        // Создаём объект-связку
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, dto.getDisciplineId());
        TeacherDiscipline td = new TeacherDiscipline();
        td.setId(tdId);
        td.setTeacher(teacher);
        td.setDiscipline(discipline);
        td.setCreatedAt(dto.getCreatedAt());
        td.setUpdatedAt(dto.getUpdatedAt());

        TeacherDiscipline saved = teacherDisciplineRepository.save(td);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDisciplineDTO> getAllDisciplinesByTeacher(Integer teacherId) {
        List<TeacherDiscipline> list = teacherDisciplineRepository.findByTeacher_TeacherId(teacherId);
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDisciplineDTO getTeacherDiscipline(Integer teacherId, Integer disciplineId) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));
        return mapToDTO(td);
    }

    @Override
    public TeacherDisciplineDTO updateTeacherDiscipline(Integer teacherId, Integer disciplineId, TeacherDisciplineDTO dto) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));

        // Обновляем только те поля, которые вам нужны
        td.setCreatedAt(dto.getCreatedAt());
        td.setUpdatedAt(dto.getUpdatedAt());

        TeacherDiscipline updated = teacherDisciplineRepository.save(td);
        return mapToDTO(updated);
    }

    @Override
    public void removeTeacherDiscipline(Integer teacherId, Integer disciplineId) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));

        teacherDisciplineRepository.delete(td);
    }

    // =========================================
    // MAP entity -> dto
    // =========================================
    private TeacherDisciplineDTO mapToDTO(TeacherDiscipline td) {
        TeacherDisciplineDTO dto = new TeacherDisciplineDTO();
        dto.setTeacherId(td.getTeacher().getTeacherId());
        dto.setDisciplineId(td.getDiscipline().getDisciplineId());
        dto.setCreatedAt(td.getCreatedAt());
        dto.setUpdatedAt(td.getUpdatedAt());
        return dto;
    }
}

