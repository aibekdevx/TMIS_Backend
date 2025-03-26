package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class AcademicDegree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer academicDegreeId;

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String degreeType;
    private String specialty;
    private int yearAwarded;   // поле из исходного YearAwarded
    private Date createdAt;
    private Date updatedAt;

    // Если нужно: private String created_by, etc.
}
