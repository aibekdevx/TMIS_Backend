package com.diploma.authservice.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TeacherDisciplineId implements Serializable {

    private Integer teacherId;
    private Integer disciplineId;

    // Геттеры/сеттеры по необходимости (либо Lombok @Getter/@Setter)
}

