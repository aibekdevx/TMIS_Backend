package com.diploma.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ForeignCognition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recognitionId;

    // Связь с Teachers
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teachers teacher;

    private String documentName;

    private Date issueDate;

    private Date createdAt;
    private Date updatedAt;
    // private String created_by;
    // private String updated_by;
}
