package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForeignCognitionDTO {

    private Integer recognitionId;  // PK
    private Integer teacherId;      // teacher_id
    private String documentName;
    private Date issueDate;
    private Date createdAt;
    private Date updatedAt;
}
