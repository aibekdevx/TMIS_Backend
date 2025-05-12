package com.diploma.authservice.repository;

import com.diploma.authservice.entity.AcademicDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Integer> {

    // Найти все степени по ID преподавателя
    List<AcademicDegree> findByTeacher_TeacherId(Integer teacherId);

    @Query("SELECT DISTINCT a.degreeType FROM AcademicDegree a WHERE a.degreeType IS NOT NULL")
    List<String> findDistinctDegreeTypes();

}


