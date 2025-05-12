// src/main/java/com/diploma/authservice/repository/DisciplineRepository.java
package com.diploma.authservice.repository;

import com.diploma.authservice.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    boolean existsByDisciplineNameIgnoreCase(String disciplineName);
}
