package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.AcademicDegreeDTO;
import com.diploma.authservice.entity.AcademicDegree;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.AcademicDegreeRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.AcademicDegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AcademicDegreeServiceImpl implements AcademicDegreeService {

    private final AcademicDegreeRepository academicDegreeRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AcademicDegreeDTO createAcademicDegreeForTeacher(Integer teacherId, AcademicDegreeDTO dto) {
        // Найти учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Создаём новую AcademicDegree
        AcademicDegree degree = new AcademicDegree();
        degree.setAcademicDegreeId(dto.getAcademicDegreeId()); // Если не нужно, уберите
        degree.setTeacher(teacher);
        degree.setDegreeType(dto.getDegreeType());
        degree.setSpecialty(dto.getSpecialty());
        degree.setYearAwarded(dto.getYearAwarded());
        degree.setCreatedAt(dto.getCreatedAt());
        degree.setUpdatedAt(dto.getUpdatedAt());

        AcademicDegree saved = academicDegreeRepository.save(degree);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDegreeDTO> getAllAcademicDegreesByTeacher(Integer teacherId) {
        List<AcademicDegree> degrees = academicDegreeRepository.findByTeacher_TeacherId(teacherId);
        return degrees.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDegreeDTO getAcademicDegreeByTeacher(Integer teacherId, Integer degreeId) {
        AcademicDegree degree = academicDegreeRepository.findById(degreeId)
                .orElseThrow(() -> new RuntimeException("AcademicDegree not found: " + degreeId));

        // Проверяем, действительно ли запись принадлежит teacherId
        if (!degree.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicDegree " + degreeId + " does not belong to teacher " + teacherId);
        }

        return mapToDTO(degree);
    }

    @Override
    public AcademicDegreeDTO updateAcademicDegreeByTeacher(Integer teacherId, Integer degreeId, AcademicDegreeDTO dto) {
        AcademicDegree degree = academicDegreeRepository.findById(degreeId)
                .orElseThrow(() -> new RuntimeException("AcademicDegree not found: " + degreeId));

        // Проверяем принадлежность
        if (!degree.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicDegree " + degreeId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля
        // (Изменять teacherId не разрешено, но при необходимости можно дать такую опцию)
        degree.setDegreeType(dto.getDegreeType());
        degree.setSpecialty(dto.getSpecialty());
        degree.setYearAwarded(dto.getYearAwarded());
        degree.setCreatedAt(dto.getCreatedAt());
        degree.setUpdatedAt(dto.getUpdatedAt());

        AcademicDegree updated = academicDegreeRepository.save(degree);
        return mapToDTO(updated);
    }

    @Override
    public void deleteAcademicDegreeByTeacher(Integer teacherId, Integer degreeId) {
        AcademicDegree degree = academicDegreeRepository.findById(degreeId)
                .orElseThrow(() -> new RuntimeException("AcademicDegree not found: " + degreeId));

        if (!degree.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicDegree " + degreeId + " does not belong to teacher " + teacherId);
        }

        academicDegreeRepository.delete(degree);
    }

    // =========================================
    // МАППИНГ: ENTITY -> DTO
    // =========================================
    private AcademicDegreeDTO mapToDTO(AcademicDegree degree) {
        AcademicDegreeDTO dto = new AcademicDegreeDTO();
        dto.setAcademicDegreeId(degree.getAcademicDegreeId());
        dto.setTeacherId(degree.getTeacher().getTeacherId());
        dto.setDegreeType(degree.getDegreeType());
        dto.setSpecialty(degree.getSpecialty());
        dto.setYearAwarded(degree.getYearAwarded());
        dto.setCreatedAt(degree.getCreatedAt());
        dto.setUpdatedAt(degree.getUpdatedAt());
        return dto;
    }

}

