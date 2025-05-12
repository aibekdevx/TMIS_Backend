package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDisciplineDTO {

    private Integer teacherId;
    private Integer disciplineId;
    private String disciplineName;
    private Date createdAt;
    private Date updatedAt;
    // private String createdBy;
    // private String updatedBy;
}

