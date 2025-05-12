// src/main/java/com/diploma/authservice/service/impl/TeacherDisciplineServiceImpl.java
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
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));
        Discipline discipline = disciplineRepository.findById(dto.getDisciplineId())
                .orElseThrow(() -> new RuntimeException("Discipline not found: " + dto.getDisciplineId()));

        TeacherDiscipline td = toEntity(dto);
        td.setTeacher(teacher);
        td.setDiscipline(discipline);

        TeacherDiscipline saved = teacherDisciplineRepository.save(td);
        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDisciplineDTO> getAllDisciplinesByTeacher(Integer teacherId) {
        return teacherDisciplineRepository
                .findByTeacher_TeacherId(teacherId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDisciplineDTO getTeacherDiscipline(Integer teacherId, Integer disciplineId) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));
        return toDto(td);
    }

    @Override
    public TeacherDisciplineDTO updateTeacherDiscipline(Integer teacherId, Integer disciplineId, TeacherDisciplineDTO dto) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));

        td.setCreatedAt(dto.getCreatedAt());
        td.setUpdatedAt(dto.getUpdatedAt());
        return toDto(teacherDisciplineRepository.save(td));
    }

    @Override
    public void removeTeacherDiscipline(Integer teacherId, Integer disciplineId) {
        TeacherDisciplineId tdId = new TeacherDisciplineId(teacherId, disciplineId);
        TeacherDiscipline td = teacherDisciplineRepository.findById(tdId)
                .orElseThrow(() -> new RuntimeException(
                        "Record not found for teacherId=" + teacherId + " and disciplineId=" + disciplineId));
        teacherDisciplineRepository.delete(td);
    }

    @Transactional(readOnly = true)
    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDisciplineDTO> getAllByDisciplineId(Integer disciplineId) {
        return teacherDisciplineRepository
                .findByDiscipline_DisciplineId(disciplineId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ====================================
    // Private mapping methods
    // ====================================
    private TeacherDisciplineDTO toDto(TeacherDiscipline td) {
        TeacherDisciplineDTO dto = new TeacherDisciplineDTO();
        dto.setTeacherId(td.getTeacher().getTeacherId());
        dto.setDisciplineId(td.getDiscipline().getDisciplineId());
        dto.setDisciplineName(td.getDiscipline().getDisciplineName());
        dto.setCreatedAt(td.getCreatedAt());
        dto.setUpdatedAt(td.getUpdatedAt());
        return dto;
    }

    private TeacherDiscipline toEntity(TeacherDisciplineDTO dto) {
        TeacherDiscipline td = new TeacherDiscipline();
        td.setId(new TeacherDisciplineId(dto.getTeacherId(), dto.getDisciplineId()));
        td.setCreatedAt(dto.getCreatedAt());
        td.setUpdatedAt(dto.getUpdatedAt());
        return td;
    }
}
