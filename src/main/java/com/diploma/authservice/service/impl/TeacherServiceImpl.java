package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.TeacherDTO;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.TeacherRepository;
import com.diploma.authservice.service.TeacherService;
import com.diploma.authservice.user.Role;
import com.diploma.authservice.user.User;
import com.diploma.authservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        // Проверяем, что в DTO передан user с непустым userId
        if (teacherDTO.getUser() == null || teacherDTO.getUser().getUserId() == null) {
            throw new IllegalArgumentException("Для создания учителя необходимо передать существующий userId");
        }
        Integer userId = teacherDTO.getUser().getUserId();

        // Получаем управляемый User
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Teachers teacher = mapToEntity(teacherDTO, user);
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
    public TeacherDTO updateTeacher(Integer teacherId, TeacherDTO teacherDTO) {
        Teachers teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        // Проверяем, что в DTO передан user с непустым userId
        if (teacherDTO.getUser() == null || teacherDTO.getUser().getUserId() == null) {
            throw new IllegalArgumentException("Для обновления учителя необходимо передать существующий userId");
        }
        Integer userId = teacherDTO.getUser().getUserId();

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        teacher.setUser(user);
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

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> getTeachersWithFilters(String search,
                                                   String organization,
                                                   String degree,
                                                   int page,
                                                   int size) {
        return teacherRepository.findAll().stream()
                .filter(t -> {
                    boolean matches = true;
                    if (search != null && !search.isBlank()) {
                        String fullName = t.getUser().getFirstName() + " " + t.getUser().getLastName();
                        matches = fullName.toLowerCase().contains(search.toLowerCase()) ||
                                t.getUser().getEmail().toLowerCase().contains(search.toLowerCase());
                    }
                    if (matches && organization != null && !organization.isBlank()) {
                        matches = t.getJobInfos() != null &&
                                t.getJobInfos().stream()
                                        .anyMatch(j -> j.getOrganizationName() != null &&
                                                j.getOrganizationName()
                                                        .equalsIgnoreCase(organization));
                    }
                    if (matches && degree != null && !degree.isBlank()) {
                        matches = t.getAcademicDegrees() != null &&
                                t.getAcademicDegrees().stream()
                                        .anyMatch(d -> d.getDegreeType() != null &&
                                                d.getDegreeType()
                                                        .equalsIgnoreCase(degree));
                    }
                    return matches;
                })
                .skip((long) page * size)
                .limit(size)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalCount() {
        return teacherRepository.count();
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

    private Teachers mapToEntity(TeacherDTO dto, User managedUser) {
        Teachers teacher = new Teachers();
        teacher.setUser(managedUser);
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
