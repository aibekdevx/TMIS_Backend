package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class AcademicTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer academicTitleId;  // PK

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String titleName;
    private String specialty;

    // Исправим YearConderred → yearConferred для единообразия стиля
    private int yearConferred;

    private Date createdAt;
    private Date updatedAt;
}

