package com.diploma.authservice.repository;

import com.diploma.authservice.entity.ForeignCognition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForeignCognitionRepository extends JpaRepository<ForeignCognition, Integer> {

    // Найти все ForeignCognition, связанные с конкретным teacherId
    List<ForeignCognition> findByTeacher_TeacherId(Integer teacherId);
}

