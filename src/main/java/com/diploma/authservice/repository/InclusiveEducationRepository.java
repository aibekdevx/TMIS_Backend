package com.diploma.authservice.repository;

import com.diploma.authservice.entity.InclusiveEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InclusiveEducationRepository extends JpaRepository<InclusiveEducation, Integer> {

    // Найти все записи InclusiveEducation, связанные с конкретным teacherId
    List<InclusiveEducation> findByTeacher_TeacherId(Integer teacherId);
}

