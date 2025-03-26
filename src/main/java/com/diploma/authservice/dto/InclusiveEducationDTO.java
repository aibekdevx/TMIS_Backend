package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InclusiveEducationDTO {

    private Integer inclusiveEducationId; // PK
    private Integer teacherId;            // teacher_id из Teachers

    private String courses;
    private String internships;

    private Date createdAt;
    private Date updatedAt;
}
