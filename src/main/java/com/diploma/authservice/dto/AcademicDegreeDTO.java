package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDegreeDTO {

    private Integer academicDegreeId;
    private Integer teacherId;          // ID учителя
    private String degreeType;
    private String specialty;
    private int yearAwarded;
    private Date createdAt;
    private Date updatedAt;

}
