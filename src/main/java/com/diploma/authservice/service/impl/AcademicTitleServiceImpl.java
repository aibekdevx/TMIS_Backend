package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.AcademicTitleDTO;
import com.diploma.authservice.entity.AcademicTitle;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.AcademicTitleRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.AcademicTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AcademicTitleServiceImpl implements AcademicTitleService {

    private final AcademicTitleRepository academicTitleRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AcademicTitleDTO createAcademicTitleForTeacher(Integer teacherId, AcademicTitleDTO dto) {
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        AcademicTitle title = new AcademicTitle();
        title.setAcademicTitleId(dto.getAcademicTitleId()); // Если не нужно — уберите
        title.setTeacher(teacher);
        title.setTitleName(dto.getTitleName());
        title.setSpecialty(dto.getSpecialty());
        title.setYearConferred(dto.getYearConferred());
        title.setCreatedAt(dto.getCreatedAt());
        title.setUpdatedAt(dto.getUpdatedAt());

        AcademicTitle saved = academicTitleRepository.save(title);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicTitleDTO> getAllAcademicTitlesByTeacher(Integer teacherId) {
        List<AcademicTitle> titles = academicTitleRepository.findByTeacher_TeacherId(teacherId);
        return titles.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicTitleDTO getAcademicTitleByTeacher(Integer teacherId, Integer titleId) {
        AcademicTitle title = academicTitleRepository.findById(titleId)
                .orElseThrow(() -> new RuntimeException("AcademicTitle not found: " + titleId));

        // Проверяем принадлежность
        if (!title.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicTitle " + titleId + " does not belong to teacher " + teacherId);
        }
        return mapToDTO(title);
    }

    @Override
    public AcademicTitleDTO updateAcademicTitleByTeacher(Integer teacherId, Integer titleId, AcademicTitleDTO dto) {
        AcademicTitle title = academicTitleRepository.findById(titleId)
                .orElseThrow(() -> new RuntimeException("AcademicTitle not found: " + titleId));

        // Проверяем принадлежность
        if (!title.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicTitle " + titleId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля
        title.setTitleName(dto.getTitleName());
        title.setSpecialty(dto.getSpecialty());
        title.setYearConferred(dto.getYearConferred());
        title.setCreatedAt(dto.getCreatedAt());
        title.setUpdatedAt(dto.getUpdatedAt());

        AcademicTitle updated = academicTitleRepository.save(title);
        return mapToDTO(updated);
    }

    @Override
    public void deleteAcademicTitleByTeacher(Integer teacherId, Integer titleId) {
        AcademicTitle title = academicTitleRepository.findById(titleId)
                .orElseThrow(() -> new RuntimeException("AcademicTitle not found: " + titleId));

        if (!title.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("AcademicTitle " + titleId + " does not belong to teacher " + teacherId);
        }

        academicTitleRepository.delete(title);
    }

    // =========================================
    // MAP ENTITY -> DTO
    // =========================================
    private AcademicTitleDTO mapToDTO(AcademicTitle title) {
        AcademicTitleDTO dto = new AcademicTitleDTO();
        dto.setAcademicTitleId(title.getAcademicTitleId());
        dto.setTeacherId(title.getTeacher().getTeacherId());
        dto.setTitleName(title.getTitleName());
        dto.setSpecialty(title.getSpecialty());
        dto.setYearConferred(title.getYearConferred());
        dto.setCreatedAt(title.getCreatedAt());
        dto.setUpdatedAt(title.getUpdatedAt());
        return dto;
    }

}

