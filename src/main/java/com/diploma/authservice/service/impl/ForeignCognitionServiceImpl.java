package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.ForeignCognitionDTO;
import com.diploma.authservice.entity.ForeignCognition;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.ForeignCognitionRepository;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.ForeignCognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ForeignCognitionServiceImpl implements ForeignCognitionService {

    private final ForeignCognitionRepository foreignCognitionRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ForeignCognitionDTO createForeignCognitionForTeacher(Integer teacherId, ForeignCognitionDTO dto) {
        // Проверяем наличие учителя
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        // Создаём новую запись
        ForeignCognition cognition = new ForeignCognition();
        cognition.setRecognitionId(dto.getRecognitionId()); // Уберите, если генерируется автоматически
        cognition.setTeacher(teacher);
        cognition.setDocumentName(dto.getDocumentName());
        cognition.setIssueDate(dto.getIssueDate());
        cognition.setCreatedAt(dto.getCreatedAt());
        cognition.setUpdatedAt(dto.getUpdatedAt());

        ForeignCognition saved = foreignCognitionRepository.save(cognition);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForeignCognitionDTO> getAllForeignCognitionsByTeacher(Integer teacherId) {
        List<ForeignCognition> list = foreignCognitionRepository.findByTeacher_TeacherId(teacherId);
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ForeignCognitionDTO getForeignCognitionByTeacher(Integer teacherId, Integer recognitionId) {
        ForeignCognition cognition = foreignCognitionRepository.findById(recognitionId)
                .orElseThrow(() -> new RuntimeException("ForeignCognition not found: " + recognitionId));

        if (!cognition.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("ForeignCognition " + recognitionId + " does not belong to teacher " + teacherId);
        }
        return mapToDTO(cognition);
    }

    @Override
    public ForeignCognitionDTO updateForeignCognitionByTeacher(Integer teacherId, Integer recognitionId, ForeignCognitionDTO dto) {
        ForeignCognition cognition = foreignCognitionRepository.findById(recognitionId)
                .orElseThrow(() -> new RuntimeException("ForeignCognition not found: " + recognitionId));

        // Проверяем принадлежность
        if (!cognition.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("ForeignCognition " + recognitionId + " does not belong to teacher " + teacherId);
        }

        // Обновляем поля
        cognition.setDocumentName(dto.getDocumentName());
        cognition.setIssueDate(dto.getIssueDate());
        cognition.setCreatedAt(dto.getCreatedAt());
        cognition.setUpdatedAt(dto.getUpdatedAt());

        ForeignCognition updated = foreignCognitionRepository.save(cognition);
        return mapToDTO(updated);
    }

    @Override
    public void deleteForeignCognitionByTeacher(Integer teacherId, Integer recognitionId) {
        ForeignCognition cognition = foreignCognitionRepository.findById(recognitionId)
                .orElseThrow(() -> new RuntimeException("ForeignCognition not found: " + recognitionId));

        if (!cognition.getTeacher().getTeacherId().equals(teacherId)) {
            throw new RuntimeException("ForeignCognition " + recognitionId + " does not belong to teacher " + teacherId);
        }

        foreignCognitionRepository.delete(cognition);
    }

    // ===== MAP Entity -> DTO =====
    private ForeignCognitionDTO mapToDTO(ForeignCognition entity) {
        ForeignCognitionDTO dto = new ForeignCognitionDTO();
        dto.setRecognitionId(entity.getRecognitionId());
        dto.setTeacherId(entity.getTeacher().getTeacherId());
        dto.setDocumentName(entity.getDocumentName());
        dto.setIssueDate(entity.getIssueDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
