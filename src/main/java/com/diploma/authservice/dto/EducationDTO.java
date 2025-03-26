package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    private Integer educationId;

    // ID учителя, к которому относится данная запись
    private Integer teacherId;

    private String institutionName;
    private String specialty;
    private String diplomaQualification;
    private int startYear;
    private int endYear;
    private String certificateNumber;
    private Date createdAt;
    private Date updatedAt;
}

