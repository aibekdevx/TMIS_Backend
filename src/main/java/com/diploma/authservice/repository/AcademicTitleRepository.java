package com.diploma.authservice.repository;

import com.diploma.authservice.entity.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicTitleRepository extends JpaRepository<AcademicTitle, Integer> {
    // Найти все AcademicTitle, связанные с конкретным teacherId
    List<AcademicTitle> findByTeacher_TeacherId(Integer teacherId);
}

