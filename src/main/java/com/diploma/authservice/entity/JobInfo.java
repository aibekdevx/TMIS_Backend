package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class JobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String organizationName;

    private String organizationAddress;

    private String position;

    private Integer workExperience;

    private Boolean mainWorkFlag;

    private Date createdAt;
    // private String created_by; // при необходимости

    private Date updatedAt;
    // private String updated_by; // при необходимости
}

