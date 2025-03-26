package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class InclusiveEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inclusive_education_id;

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String courses;
    private String internships;

    private Date createdAt;
    private Date updatedAt;
    // private String created_by;
    // private String updated_by;
}

