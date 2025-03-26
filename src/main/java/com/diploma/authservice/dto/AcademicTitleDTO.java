package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicTitleDTO {

    private Integer academicTitleId;
    private Integer teacherId;       // ID учителя
    private String titleName;
    private String specialty;
    private int yearConferred;
    private Date createdAt;
    private Date updatedAt;
}

