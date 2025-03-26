package com.diploma.authservice.repository;

import com.diploma.authservice.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Integer> {
    // При необходимости можно добавить кастомные запросы
    // напр.: List<Teachers> findByPlaceOfBirth(String placeOfBirth);
}

