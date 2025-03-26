package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String institutionName;
    private String specialty;
    private String diplomaQualification;

    // Переименуем StartYear / EndYear -> startYear / endYear (в Java обычно поля пишутся с маленькой буквы)
    private int startYear;
    private int endYear;

    private String certificateNumber;

    private Date createdAt;
    private Date updatedAt;
    // private String created_by;
    // private String updated_by;
}

