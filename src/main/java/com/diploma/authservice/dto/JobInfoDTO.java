package com.diploma.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoDTO {

    private Integer jobId;

    // В DTO храним ID учителя
    private Integer teacherId;

    private String organizationName;

    private String organizationAddress;

    private String position;

    private Integer workExperience;

    private Boolean mainWorkFlag;

    private Date createdAt;
    // private String createdBy;

    private Date updatedAt;
    // private String updatedBy;
}

