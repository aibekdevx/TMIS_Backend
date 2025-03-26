package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.InclusiveEducationDTO;
import com.diploma.authservice.entity.InclusiveEducation;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.InclusiveEducationRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.InclusiveEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InclusiveEducationServiceImpl implements InclusiveEducationService {

    private final InclusiveEducationRepository inclusiveEducationRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public InclusiveEducationDTO createInclusiveEducationForTeacher(Integer teacherId, InclusiveEducationDTO dto) {
        // Проверяем наличие учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Создаём новую запись InclusiveEducation
        InclusiveEducation entity = new InclusiveEducation();
        entity.setInclusive_education_id(dto.getInclusiveEducationId()); // уберите, если генерируется автоматически
        entity.setTeacher(teacher);
        entity.setCourses(dto.getCourses());
        entity.setInternships(dto.getInternships());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        InclusiveEducation saved = inclusiveEducationRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InclusiveEducationDTO> getAllInclusiveEducationsByTeacher(Integer teacherId) {
        List<InclusiveEducation> list = inclusiveEducationRepository.findByTeacher_TeacherId(teacherId);
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InclusiveEducationDTO getInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId) {
        InclusiveEducation entity = inclusiveEducationRepository.findById(inclusiveEducationId)
                .orElseThrow(() -> new RuntimeException("InclusiveEducation not found: " + inclusiveEducationId));

        if (!entity.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("InclusiveEducation " + inclusiveEducationId + " does not belong to teacher " + teacherId);
        }
        return mapToDTO(entity);
    }

    @Override
    public InclusiveEducationDTO updateInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId, InclusiveEducationDTO dto) {
        InclusiveEducation entity = inclusiveEducationRepository.findById(inclusiveEducationId)
                .orElseThrow(() -> new RuntimeException("InclusiveEducation not found: " + inclusiveEducationId));

        // Проверяем принадлежность учителю
        if (!entity.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("InclusiveEducation " + inclusiveEducationId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля
        entity.setCourses(dto.getCourses());
        entity.setInternships(dto.getInternships());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        InclusiveEducation updated = inclusiveEducationRepository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public void deleteInclusiveEducationByTeacher(Integer teacherId, Integer inclusiveEducationId) {
        InclusiveEducation entity = inclusiveEducationRepository.findById(inclusiveEducationId)
                .orElseThrow(() -> new RuntimeException("InclusiveEducation not found: " + inclusiveEducationId));

        if (!entity.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("InclusiveEducation " + inclusiveEducationId + " does not belong to teacher " + teacherId);
        }

        inclusiveEducationRepository.delete(entity);
    }

    // =======================================
    // MAP Entity -> DTO
    // =======================================
    private InclusiveEducationDTO mapToDTO(InclusiveEducation entity) {
        InclusiveEducationDTO dto = new InclusiveEducationDTO();
        dto.setInclusiveEducationId(entity.getInclusive_education_id());
        dto.setTeacherId(entity.getTeacher().getTeacherId());
        dto.setCourses(entity.getCourses());
        dto.setInternships(entity.getInternships());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}

