package com.diploma.authservice.repository;

import com.diploma.authservice.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    // Найти все записи Education, связанные с конкретным teacherId
    List<Education> findByTeacher_TeacherId(Integer teacherId);
}

