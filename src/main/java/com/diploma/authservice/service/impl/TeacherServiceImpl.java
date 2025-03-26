package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.TeacherDTO;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teachers teacher = mapToEntity(teacherDTO);
        Teachers saved = teacherRepository.save(teacher);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDTO getTeacherById(Integer teacherId) {
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));
        return mapToDTO(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(Integer teacherId, TeacherDTO teacherDTO) {
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        // Обновляем поля из DTO
        teacher.setUser(teacherDTO.getUser());
        teacher.setDateOfBirth(teacherDTO.getDateOfBirth());
        teacher.setPlaceOfBirth(teacherDTO.getPlaceOfBirth());
        teacher.setCriminalRecord(teacherDTO.getCriminalRecord());
        teacher.setHasMedicalBook(teacherDTO.getHasMedicalBook());
        teacher.setMilitaryRank(teacherDTO.getMilitaryRank());
        teacher.setOtherTitles(teacherDTO.getOtherTitles());
        teacher.setCreatedAt(teacherDTO.getCreatedAt());
        teacher.setUpdatedAt(teacherDTO.getUpdatedAt());

        Teachers updated = teacherRepository.save(teacher);
        return mapToDTO(updated);
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    private TeacherDTO mapToDTO(Teachers teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setUser(teacher.getUser());
        dto.setDateOfBirth(teacher.getDateOfBirth());
        dto.setPlaceOfBirth(teacher.getPlaceOfBirth());
        dto.setCriminalRecord(teacher.getCriminalRecord());
        dto.setHasMedicalBook(teacher.getHasMedicalBook());
        dto.setMilitaryRank(teacher.getMilitaryRank());
        dto.setOtherTitles(teacher.getOtherTitles());
        dto.setCreatedAt(teacher.getCreatedAt());
        dto.setUpdatedAt(teacher.getUpdatedAt());
        return dto;
    }

    private Teachers mapToEntity(TeacherDTO dto) {
        Teachers teacher = new Teachers();
        teacher.setTeacherId(dto.getTeacherId());
        teacher.setUser(dto.getUser());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setPlaceOfBirth(dto.getPlaceOfBirth());
        teacher.setCriminalRecord(dto.getCriminalRecord());
        teacher.setHasMedicalBook(dto.getHasMedicalBook());
        teacher.setMilitaryRank(dto.getMilitaryRank());
        teacher.setOtherTitles(dto.getOtherTitles());
        teacher.setCreatedAt(dto.getCreatedAt());
        teacher.setUpdatedAt(dto.getUpdatedAt());
        return teacher;
    }
}
