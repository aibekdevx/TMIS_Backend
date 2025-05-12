package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "teacher_discipline") // если таблица в БД называется именно teacher_discipline
public class
TeacherDiscipline {

    @EmbeddedId
    private TeacherDisciplineId id;

    // Связь к Teachers
    @ManyToOne
    @MapsId("teacherId") // говорит JPA: использовать поле teacherId из TeacherDisciplineId
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    // Связь к Discipline
    @ManyToOne
    @MapsId("disciplineId") // использовать поле disciplineId из TeacherDisciplineId
    @JoinColumn(name = "discipline_id", referencedColumnName = "disciplineId")
    private Discipline discipline;

    private Date createdAt;
    private Date updatedAt;
    // private String created_by;
    // private String updated_by;
}

