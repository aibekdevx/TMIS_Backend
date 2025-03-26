package com.diploma.authservice.repository;

import com.diploma.authservice.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    // при необходимости – дополнительные методы поиска
}

