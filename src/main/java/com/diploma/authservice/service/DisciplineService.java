// src/main/java/com/diploma/authservice/service/DisciplineService.java
package com.diploma.authservice.service;

import com.diploma.authservice.dto.DisciplineDTO;

import java.util.List;

public interface DisciplineService {

    List<DisciplineDTO> getAll();

    DisciplineDTO create(DisciplineDTO dto);

    DisciplineDTO update(Integer id, DisciplineDTO dto);

    void delete(Integer id);
}
