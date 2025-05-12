// src/main/java/com/diploma/authservice/service/impl/DisciplineServiceImpl.java
package com.diploma.authservice.service.impl;

import com.diploma.authservice.dto.DisciplineDTO;
import com.diploma.authservice.entity.Discipline;
import com.diploma.authservice.repository.DisciplineRepository;
import com.diploma.authservice.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository repo;

    @Override
    public List<DisciplineDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplineDTO create(DisciplineDTO dto) {
        if (repo.existsByDisciplineNameIgnoreCase(dto.getDisciplineName()))
            throw new IllegalArgumentException("Discipline already exists");

        Discipline saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public DisciplineDTO update(Integer id, DisciplineDTO dto) {
        Discipline d = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        d.setDisciplineName(dto.getDisciplineName());
        return toDto(repo.save(d));
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    /* ---------- mapping (private) ---------- */

    private DisciplineDTO toDto(Discipline e) {
        DisciplineDTO dto = new DisciplineDTO();
        dto.setDisciplineId(e.getDisciplineId());
        dto.setDisciplineName(e.getDisciplineName());
        return dto;
    }

    private Discipline toEntity(DisciplineDTO dto) {
        Discipline e = new Discipline();
        e.setDisciplineId(dto.getDisciplineId()); // null для create
        e.setDisciplineName(dto.getDisciplineName());
        return e;
    }
}
